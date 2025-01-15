package com.aluracursos.literalura.services;

import com.aluracursos.literalura.model.LibroRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvertirDatos implements IConvertirDatos  {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir JSON a " + clase.getSimpleName(), e);
        }
    }

    public String extraerResultadosComoJson(String json) {
        try {
            // Leer el JSON como un árbol
            JsonNode rootNode = mapper.readTree(json);

            // Imprimir todo el JSON para inspección
            System.out.println("JSON completo recibido: " + rootNode.toString());

            // Extraer el nodo "results"
            JsonNode resultsNode = rootNode.get("results");

            System.out.println("json mapeado: " + resultsNode);

            if (resultsNode == null || resultsNode.isEmpty()) {
                throw new RuntimeException("El nodo 'results' no existe o está vacío");
            }

            // Convertir el nodo "results" nuevamente a JSON y devolverlo como String
            return mapper.writeValueAsString(resultsNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al extraer el contenido de 'results' del JSON", e);
        }
    }

    public <T> List<T> obtenerListaDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clase));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir JSON a lista de " + clase.getSimpleName(), e);
        }
    }
}
