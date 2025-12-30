package com.github.gtvnv.libraryapi.controller.mappers;

import com.github.gtvnv.libraryapi.controller.dto.UsuarioDTO;
import com.github.gtvnv.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
