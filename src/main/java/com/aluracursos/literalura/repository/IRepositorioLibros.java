package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//Para trabajar con la base de datos
public interface IRepositorioLibros {
    @Query("SELECT l FROM libro l WHERE l.titulo ILIKE %:titulo%")
    List<Libro> findByTitulo(String titulo);

}
