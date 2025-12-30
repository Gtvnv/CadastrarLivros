package com.github.gtvnv.libraryapi.controller;

import com.github.gtvnv.libraryapi.controller.dto.AutorDTO;
import com.github.gtvnv.libraryapi.controller.mappers.AutorMapper;
import com.github.gtvnv.libraryapi.model.Autor;
import com.github.gtvnv.libraryapi.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // <--- Importante: É @Controller, não @RestController
@RequestMapping("cadastro-autor") // A rota que definimos no Security e no HTML
@RequiredArgsConstructor
public class AutorViewController {

    private final AutorService service;
    private final AutorMapper mapper;

    @GetMapping
    public String abrirFormulario(Model model) {
        model.addAttribute("autorDto", AutorDTO.vazio()); // Fica bem mais legível
        return "cadastro-autor";
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public String salvar(@ModelAttribute AutorDTO dto) {
        // @ModelAttribute converte os dados do form HTML para o objeto Java
        Autor autor = mapper.toEntity(dto);
        service.salvar(autor);

        // Redireciona de volta para a lista ou para o cadastro (limpo) após salvar
        return "redirect:/cadastro-autor";
    }
}