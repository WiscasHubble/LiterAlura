package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.LibroRecord;
import com.aluracursos.literalura.repository.IRepositorioAutores;
import com.aluracursos.literalura.repository.IRepositorioLibros;
import com.aluracursos.literalura.services.ConsumoAPI;
import com.aluracursos.literalura.services.ConvertirDatos;
import com.aluracursos.literalura.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvertirDatos convertirDatos = new ConvertirDatos();
    LibroService libroService = new LibroService();


    String urlBase = "https://gutendex.com/books?";


    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Busqueda de libro por titulo
                    2 - Lista de todos los libros en la BD
                    3 - Listado de libros en base a un idioma
                    3 - Buscar Obra mejor valorada por nombre de Autor
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
                    Prueba();
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



    private void Prueba() {
        libroService.obtenerPrimerLibro();
    }


}
