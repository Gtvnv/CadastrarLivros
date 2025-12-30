package com.github.gtvnv.libraryapi.controller;

import com.github.gtvnv.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.gtvnv.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.github.gtvnv.libraryapi.controller.mappers.LivroMapper;
import com.github.gtvnv.libraryapi.model.Autor;
import com.github.gtvnv.libraryapi.model.GeneroLivro;
import com.github.gtvnv.libraryapi.model.Livro;
import com.github.gtvnv.libraryapi.service.AutorService;
import com.github.gtvnv.libraryapi.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class LivroViewController {

    private final LivroService livroService;
    private final AutorService autorService;
    private final LivroMapper mapper; // Precisamos do mapper para a listagem

    // --- CADASTRO (GET) ---
    @GetMapping("/cadastro-livro")
    public String abrirFormulario(Model model) {
        model.addAttribute("livroDto", CadastroLivroDTO.vazio());
        model.addAttribute("listaAutores", autorService.listarTodos());
        model.addAttribute("generos", GeneroLivro.values());
        return "cadastro-livro";
    }

    // --- SALVAR (POST) ---
    @PostMapping("/cadastro-livro")
    public String salvar(@ModelAttribute CadastroLivroDTO dto, Model model) {
        try {
            Autor autor = autorService.obterPorId(dto.idAutor())
                    .orElseThrow(() -> new IllegalArgumentException("Autor inválido"));

            Livro livro = new Livro();
            livro.setTitulo(dto.titulo());
            livro.setIsbn(dto.isbn());
            livro.setDataPublicacao(dto.dataPublicacao());
            livro.setPreco(dto.preco());
            livro.setGenero(dto.genero()); // <--- NOVO: Salva o gênero
            livro.setAutor(autor);

            livroService.salvar(livro);

            return "redirect:/livros";

        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("livroDto", dto);
            model.addAttribute("listaAutores", autorService.listarTodos());
            // --- NOVO: Se der erro, manda a lista de gêneros de novo ---
            model.addAttribute("generos", GeneroLivro.values());

            return "cadastro-livro";
        }
    }

    // --- LISTAGEM (Trazido do PageController e ajustado para /livros) ---
    @GetMapping("/livros")
    public String listarLivros(
            @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina,
            Model model) {

        // Chama o serviço de pesquisa (traz todos se os filtros forem nulos)
        Page<Livro> pageResult = livroService.pesquisa(null, null, null, null, null, pagina, tamanhoPagina);

        // Converte as Entidades para DTOs de visualização
        List<ResultadoPesquisaLivroDTO> listaDTOs = pageResult.getContent()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        // Manda os dados para o HTML (lista-livros.html)
        model.addAttribute("livros", listaDTOs);
        model.addAttribute("paginaAtual", pagina);
        model.addAttribute("totalPaginas", pageResult.getTotalPages());
        model.addAttribute("temProxima", pageResult.hasNext());
        model.addAttribute("temAnterior", pageResult.hasPrevious());

        return "listagem"; // Nome do arquivo HTML da listagem
    }

    @PostMapping("/deletar") // O HTML usa POST porque form não suporta DELETE nativo sem config extra
    public String deletar(@RequestParam("id") UUID id) {
        livroService.obterPorId(id).ifPresent(livro ->
                        livroService.deletar(livro)
        );
        return "redirect:/livros";
    }

    @GetMapping("/editar-livro/{id}")
    public String editar(@PathVariable("id") UUID id, Model model) {
        // Busca o livro existente
        CadastroLivroDTO livroExistente = livroService.obterPorId(id) // Você precisará criar um método que retorne DTO no service ou converter aqui
                .map(livro -> new CadastroLivroDTO(
                        livro.getTitulo(),
                        livro.getIsbn(),
                        livro.getDataPublicacao(),
                        livro.getGenero(),
                        livro.getPreco(),
                        livro.getAutor().getId()
                )).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

        model.addAttribute("livroDto", livroExistente);
        model.addAttribute("listaAutores", autorService.listarTodos());
        model.addAttribute("generos", GeneroLivro.values());

        // Vamos reusar a tela de cadastro!
        return "cadastro-livro";
    }

    // TODO: Criar a lógica do deletar e do atulizar
}