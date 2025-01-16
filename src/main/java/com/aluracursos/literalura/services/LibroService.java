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


//    public List<Libro> convertirRecordsAEntidades(List<LibroRecord> librosRecord) {
//        var debug = librosRecord.stream()
//                .map(Libro::new) // Usa el constructor de Libro que acepta un LibroRecord
//                .collect(Collectors.toList());
//        System.out.println(debug);
//
//        return debug;
//    }


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
            String idioma = libroRecord.idiomas(); // Ya es un String

            // Asignamos el primer idioma al libro como una lista de un solo idioma
            libro.setIdiomas(Collections.singletonList(idioma));

            // Crear el objeto Autor (suponiendo que solo hay un autor)
            Autor autor = new Autor();
            autor.setNombre("Dostoyevsky, Fyodor");
            autor.setNacimiento(1821);
            autor.setFallecimiento(1881);

            // Asignamos el autor al libro
            libro.setAutores(List.of(autor));

            System.out.println(libro);

            return libro;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Libro guardarLibro(Libro libro) {
        // Guarda el libro junto con los autores si es necesario
        for (Autor autor : libro.getAutores()) {
            autoresRepositorio.save(autor);
        }
        return librosRepositorio.save(libro);
    }

    public Autor guardarAutor(Autor autor) {
        return autoresRepositorio.save(autor);
    }


}
