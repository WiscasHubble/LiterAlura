package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.LibroRecord;
import com.aluracursos.literalura.repository.IRepositorioLibros;
import com.aluracursos.literalura.services.ConsumoAPI;
import com.aluracursos.literalura.services.ConvertirDatos;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvertirDatos convertirDatos = new ConvertirDatos();
    private IRepositorioLibros repositorioLibros;
    String urlBase = "https://gutendex.com/books?";


    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Busqueda de libro por titulo
                    2 - Lista de todos los libros en la BD
                    3 - Buscar Autor por Nombre
                    4 - Lista de autores
                    5 - Listar autores en determinado ano
                    6 - Exhibir cantidad de libros en un determinado idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
//                case 2:
//                    buscarEpisodioPorSerie();
//                    break;
//                case 3:
//                    mostrarSeriesBuscadas();
//                    break;
//                case 4:
//                    buscarSeriesPorTitulo();
//                    break;
//                case 5:
//                    buscarTop5Series();
//                    break;
//                case 6:
//                    buscarSeriesPorCategoria();
//                    break;
//                case 7:
//                    filtrarSeriesPorTemporadaYEvaluacion();
//                    break;
//                case 8:
//                    buscarEpisodiosPorTitulo();
//                    break;
//                case 9:
//                    buscarTop5Episodios();
//                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private List<LibroRecord> buscarLibrosPorTitulo() {
        System.out.println("Escribe el título del libro que deseas buscar:");
        String tituloLibro = teclado.nextLine();
        String url = urlBase + "search=" + tituloLibro.replace(" ", "%20");

        var json = consumoAPI.obtenerDatos(url);
        System.out.println("Respuesta JSON: " + json);

        if (json == null || json.isEmpty()) {
            throw new RuntimeException("La respuesta está vacía o nula.");
        }

        var jsonProcesado = convertirDatos.extraerResultadosComoJson(json);

        // Deserializar el array de libros
        try {
            List<LibroRecord> libros = convertirDatos.obtenerListaDatos(jsonProcesado, LibroRecord.class);
            System.out.println("Datos extraídos: " + libros);
            return libros;
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar los datos del servidor.", e);
        }
    }




}