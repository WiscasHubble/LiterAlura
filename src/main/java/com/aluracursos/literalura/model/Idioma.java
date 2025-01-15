package com.aluracursos.literalura.model;

public enum Idioma {
    ES("es", "espanol"),    // Español
    EN("en", "ingles"),    // Inglés
    FR("fr", "frances"),    // Francés
    DE("de", "aleman");    // Alemán

    private final String idiomaGutendex;
    private final String idiomaEspanol;

    Idioma(String idiomaGutendex, String idiomaEspanol) {
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaEspanol = idiomaEspanol;
    }

    public String getidiomaGutendex() {
        return idiomaGutendex;
    }
    public String getidiomaEspanol() {
        return idiomaEspanol;
    }

    public static Idioma fromIdiomaGutendex(String idiomaGutendex) {
        for (Idioma idioma : values()) {
            if (idioma.getidiomaGutendex().equalsIgnoreCase(idiomaGutendex)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma no válido: " + idiomaGutendex);
    }

    public static Idioma fromIdiomaEspanol(String idiomaEspanol) {
        for (Idioma idioma : values()) {
            if (idioma.getidiomaEspanol().equalsIgnoreCase(idiomaEspanol)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma no válido: " + idiomaEspanol);
    }
}

