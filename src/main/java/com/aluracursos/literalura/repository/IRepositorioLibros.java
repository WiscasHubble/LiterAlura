package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositorioLibros extends JpaRepository<Libro, Long> {

    @Query(value = "SELECT * FROM libro", nativeQuery = true)
    List<Libro> findAllBooks();

    @Query(value = "SELECT * FROM libro WHERE idiomas = :idioma", nativeQuery = true)
    List<Libro> findBooksByLanguage(@Param("idioma") String idioma);

    @Query(value = "SELECT * FROM libro ORDER BY descargas DESC LIMIT 10", nativeQuery = true)
    List<Libro> findTop10BooksByDownloads();

    @Query(value = "SELECT COUNT(*) FROM libro WHERE idiomas = :idioma", nativeQuery = true)
    int countBooksByLanguage(@Param("idioma") String idioma);


}
