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
    IRepositorioAutores repositorioAutores;





    public Libro obtenerPrimerLibro(String json) {
        try {
            // Simulamos la respuesta de la API
            String jsonResponse = json;


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

            // Obtener los autores desde la funcion
            Autor autor = obtenerAutor(json);

            // Asignamos los autores al libro
            libro.setAutor(autor);

            System.out.println(libro);
//            System.out.println(autores);

            return libro;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Autor obtenerAutor(String json){
        try {
            // Simulamos la respuesta de la API
            String jsonResponse = json;

            // Inicializamos el ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Convertimos la respuesta JSON en una lista de LibroRecord
            List<LibroRecord> libroRecords = objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, LibroRecord.class));

            // Accedemos al primer libro
            LibroRecord libroRecord = libroRecords.get(0);


            // Obtener los autores desde el JSON
            List<Autor> autores = new ArrayList<>();
            for (var author : libroRecords.get(0).authors()) {
                Autor autor = new Autor();
                autor.setNombre(author.name());
                autor.setNacimiento(author.birth_year());
                autor.setFallecimiento(author.death_year());
                autores.add(autor);
            }


            return autores.get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
