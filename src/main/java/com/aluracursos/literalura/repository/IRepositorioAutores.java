package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositorioAutores extends JpaRepository<Autor, Long> {

    @Query(value = "SELECT nombre FROM autor", nativeQuery = true)
    List<String> findAllAuthors();

    @Query(value = "SELECT nombre FROM autor WHERE nacimiento <= :anio AND (fallecimiento IS NULL OR fallecimiento > :anio)", nativeQuery = true)
    List<String> findLivingAuthorsByYear(@Param("anio") int year);

}
