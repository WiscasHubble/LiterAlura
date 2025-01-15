package com.aluracursos.literalura.services;



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

//Reqquest hacia la URL
public class ConsumoAPI {
    public String obtenerDatos(String url) {
        // Crear el cliente HTTP configurado para seguir redirecciones
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)  // Asegura que las redirecciones sean seguidas
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener los datos desde la API", e);
        }

        if (response != null && response.body() != null) {
            return response.body();
        } else {
            throw new RuntimeException("La respuesta de la API es vacía");
        }
    }
}
