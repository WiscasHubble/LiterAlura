package com.aluracursos.literalura.services;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.LibroRecord;
import com.aluracursos.literalura.repository.IRepositorioAutores;
import com.aluracursos.literalura.repository.IRepositorioLibros;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LibroService {
    @Autowired
    private IRepositorioLibros librosRepositorio;

    @Autowired
    private IRepositorioAutores autoresRepositorio;
    private ConvertirDatos conversor = new ConvertirDatos();

    private static final String API_URL = "https://gutendex.com/books/";



    public Libro obtenerPrimerLibro() {
        try {
            // Simulamos la respuesta de la API
            String jsonResponse = "[{\"id\":61851,\"title\":\"El crimen y el castigo\",\"authors\":[{\"name\":\"Dostoyevsky, Fyodor\",\"birth_year\":1821,\"death_year\":1881}],\"translators\":[{\"name\":\"Pedraza y Páez, Pedro\",\"birth_year\":1877,\"death_year\":null}],\"subjects\":[\"Crime -- Psychological aspects -- Fiction\",\"Detective and mystery stories\",\"Murder -- Fiction\",\"Psychological fiction\",\"Saint Petersburg (Russia) -- Fiction\"],\"bookshelves\":[\"Browsing: Crime/Mystery\",\"Browsing: Fiction\",\"Browsing: Literature\",\"Browsing: Psychiatry/Psychology\"],\"languages\":[\"es\"],\"copyright\":false,\"media_type\":\"Text\",\"formats\":{\"text/plain\":\"https://www.gutenberg.org/ebooks/61851.txt.utf-8\",\"text/html\":\"https://www.gutenberg.org/ebooks/61851.html.images\",\"text/html; charset=iso-8859-1\":\"https://www.gutenberg.org/files/61851/61851-h/61851-h.htm\",\"application/epub+zip\":\"https://www.gutenberg.org/ebooks/61851.epub3.images\",\"application/x-mobipocket-ebook\":\"https://www.gutenberg.org/ebooks/61851.kf8.images\",\"application/rdf+xml\":\"https://www.gutenberg.org/ebooks/61851.rdf\",\"image/jpeg\":\"https://www.gutenberg.org/cache/epub/61851/pg61851.cover.medium.jpg\",\"application/octet-stream\":\"https://www.gutenberg.org/cache/epub/61851/pg61851-h.zip\"},\"download_count\":1827}]";

            // Inicializamos el ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Convertimos la respuesta JSON en una lista de LibroRecord
            List<LibroRecord> libroRecords = objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, LibroRecord.class));

            // Accedemos al primer libro
            LibroRecord libroRecord = libroRecords.get(0);

            // Crear un objeto Libro
            Libro libro = new Libro();
            libro.setTitulo(libroRecord.titulo());
            libro.setDescargas(libroRecord.descargas());

            // Obtener el primer idioma de la lista y asignarlo al campo idioma
            List<String> idiomas = libroRecord.idiomas();  // Ya es una lista de strings
            libro.setIdiomas(idiomas);

            // Obtener los autores desde el JSON
            List<Autor> autores = new ArrayList<>();
            for (var author : libroRecords.get(0).authors()) {
                Autor autor = new Autor();
                autor.setNombre(author.name());
                autor.setNacimiento(author.birth_year());
                autor.setFallecimiento(author.death_year());
                autores.add(autor);
            }

            // Asignamos los autores al libro
            libro.setAutor(autores.get(0));

            System.out.println(libro);
            System.out.println(autores);

            return libro;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
