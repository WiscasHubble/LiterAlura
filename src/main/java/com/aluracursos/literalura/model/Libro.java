package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "libro")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("title")
    private String titulo;

    @JsonProperty("languages")
    private List<String> idiomas;

    @JsonProperty("download_count")
    private Integer descargas;

    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @JsonAlias("authors")
    private List<Autor> autores;

    public Libro(LibroRecord libroRecord) {
        this.titulo = libroRecord.titulo();
        this.idiomas = Collections.singletonList(libroRecord.idiomas());
        this.descargas = libroRecord.descargas();
    }

    public Libro() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Libro encontrado: " +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idiomas=" + idiomas +
                ", descargas=" + descargas +
                ", autores=" + autores;
    }
}

