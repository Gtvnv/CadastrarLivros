package com.github.gtvnv.libraryapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String loginPage(Authentication authentication) {
        // Se o usuário já estiver logado, joga ele direto pra listagem
        // para não mostrar a tela de login de novo
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/listagem.html";
        }
        return "login"; // Vai buscar em /WEB-INF/jsp/login.jsp
    }
}