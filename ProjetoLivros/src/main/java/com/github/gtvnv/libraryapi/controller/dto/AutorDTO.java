package com.github.gtvnv.libraryapi.controller.dto;

import com.github.gtvnv.libraryapi.model.GeneroLivro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Autor")
public record AutorDTO(
        UUID id,
        @NotBlank(message = "campo obrigatorio")
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrao")
        @Schema(name = "nome")
        String nome,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "nao pode ser uma data futura")
        @Schema(name = "dataNascimento")
        LocalDate dataNascimento,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 50, min = 2, message = "campo fora do tamanho padrao")
        @Schema(name = "nacionalidade")
        String nacionalidade,
        GeneroLivro genero,
        BigDecimal preco
) {
    // Adicione este método estático
    public static AutorDTO vazio() {
        return new AutorDTO(null, "", null, "", null, null);
    }
}
