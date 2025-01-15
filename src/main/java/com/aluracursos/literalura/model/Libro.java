package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Integer descargas;

    @ManyToOne
    private Autor autor;

    public Libro(LibroRecord libroRecord) {
        this.titulo = libroRecord.titulo();
        this.idioma = Idioma.fromIdiomaEspanol(libroRecord.getIdiomaPrincipal());
        this.descargas = libroRecord.descargas();
    }

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

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idioma=" + idioma +
                ", descargas=" + descargas +
                ", autor=" + autor +
                '}';
    }
}
