package com.github.gtvnv.libraryapi.security;

import com.github.gtvnv.libraryapi.model.Usuario;
import com.github.gtvnv.libraryapi.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String SENHA_PADRAO = "321";
    private final UsuarioService usuarioService;

    // Adicionamos o repositório de contexto de segurança
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        Usuario usuario = usuarioService.obterPorEmail(email);

        if (usuario == null) {
            usuario = cadastrarUsuarioNaBase(email);
        }

        // 1. Cria a autenticação customizada
        CustomAuthentication customAuth = new CustomAuthentication(usuario);
        customAuth.setAuthenticated(true); // Garante que está true

        // 2. Cria o Contexto e define a autenticação
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(customAuth);
        SecurityContextHolder.setContext(context);

        // 3. AQUI ESTÁ O PULO DO GATO (Salva na Sessão explicitamente)
        // Isso garante que o cookie JSESSIONID tenha os dados antes do redirect
        securityContextRepository.saveContext(context, request, response);

        // 4. Redireciona
        getRedirectStrategy().sendRedirect(request, response, "/listagem.html");
    }

    private Usuario cadastrarUsuarioNaBase(String email) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setLogin(obterLoginApartirEmail(email));
        usuario.setSenha(SENHA_PADRAO);
        usuario.setRoles(List.of("OPERADOR")); // Sem prefixo ROLE_ conforme sua config

        usuarioService.salvar(usuario);
        return usuario;
    }

    private String obterLoginApartirEmail(String email) {
        return email.substring(0, email.indexOf("@"));
    }
}