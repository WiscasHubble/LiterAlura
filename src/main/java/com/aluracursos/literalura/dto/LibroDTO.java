package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.Idioma;

public record LibroDTO(
        Long id,
        String titulo,
        Idioma idioma,
        Integer descargas

) {

}
