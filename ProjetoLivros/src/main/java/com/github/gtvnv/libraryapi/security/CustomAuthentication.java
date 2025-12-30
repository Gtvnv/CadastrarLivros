package com.github.gtvnv.libraryapi.security;

import com.github.gtvnv.libraryapi.model.Usuario;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CustomAuthentication implements Authentication, Serializable { // <--- 1. IMPLEMETAR ISSO

    @Serial
    private static final long serialVersionUID = 1L; // <--- 2. ADICIONAR ISSO

    private final Usuario usuario;
    private boolean authenticated = true; // Controle de estado

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.usuario
                .getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return usuario;
    }

    @Override
    public Object getPrincipal() {
        return usuario;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated; // <--- 3. RETORNAR A VARIÁVEL
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated; // <--- 3. PERMITIR ALTERAÇÃO
    }

    @Override
    public String getName() {
        return usuario.getLogin();
    }
}