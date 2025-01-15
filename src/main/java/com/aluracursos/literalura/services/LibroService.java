package com.aluracursos.literalura.services;

import com.aluracursos.literalura.dto.LibroDTO;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.IRepositorioLibros;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibroService {
    @Autowired
    private IRepositorioLibros repository;

    public List<LibroDTO> obtenerLibroPorTitulo(String titulo) {
        List<Libro> libro = repository.findByTitulo(titulo);
        return convierteDatos(libro);
    }
    public List<LibroDTO> convierteDatos(List<Libro> libro){
        return libro.stream()
                .map(l -> new LibroDTO(l.getId(), l.getTitulo(), l.getIdioma(), l.getDescargas()))
                .collect(Collectors.toList());
    }
}
