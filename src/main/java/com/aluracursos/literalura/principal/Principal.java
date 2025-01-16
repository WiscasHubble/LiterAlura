package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.IRepositorioAutores;
import com.aluracursos.literalura.repository.IRepositorioLibros;
import com.aluracursos.literalura.services.ConsumoAPI;
import com.aluracursos.literalura.services.ConvertirDatos;
import com.aluracursos.literalura.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvertirDatos convertirDatos = new ConvertirDatos();
    LibroService libroService = new LibroService();
    @Autowired
    IRepositorioLibros repositorioLibros;
    @Autowired
    IRepositorioAutores repositorioAutores;

    private static final String API_URL = "https://gutendex.com/books/";

    public Principal(IRepositorioLibros repositorioLibros, IRepositorioAutores repositorioAutores) {
        this.repositorioLibros = repositorioLibros;
        this.repositorioAutores = repositorioAutores;
    }


    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Busqueda de libro por titulo
                    2 - Listado de todos los libros buscados
                    3 - Listado de libros en base a un idioma
                    4 - Top 10 libros mayormente descargados
                    5 - Lista de autores
                    6 - Listar autores vivos en determinado anio
                    7 - Cantidad de libros en un determinado idioma
                    
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    BusquedaPorTitulo();
                    break;
                case 2:
                    listadoLibros();
                    break;
                case 3:
                    librosSegunIdioma();
                    break;
                case 4:
                    topLibrosDescargados();
                    break;
                case 5:
                    listaAutores();
                    break;
                case 6:
                    autoresVivos();
                    break;
                case 7:
                    cantidadLibrosIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }


    private void BusquedaPorTitulo() {
        try {

            System.out.println("Escribe el titulo del libro que deseas buscar: ");
            var titulo = teclado.nextLine();
            var URL = API_URL + "?search=" + titulo.replaceAll(" ", "%20");
//            System.out.println("URL: " + URL);
            var JSON = consumoAPI.obtenerDatos(URL);
//            System.out.println("JSON PRINCIPAL: " + JSON);
            var mappedJSON = convertirDatos.extraerResultadosComoJson(JSON);
//            System.out.println("JSON MAPEADO: " + mappedJSON);
            Libro libro = libroService.obtenerPrimerLibro(mappedJSON);
//            Autor autor = libroService.obtenerAutor(mappedJSON);
//            libro.setAutor(autor);
//            repositorioAutores.save(autor);
            repositorioLibros.save(libro);
        } catch (Exception e) {
            System.out.println("No se encuentra un libro con ese titulo!");
        }
    }

    private void listadoLibros() {
        try{
        List<Libro> libros = repositorioLibros.findAllBooks();
        libros.forEach(libro -> System.out.println(libro));

        } catch (Exception e) {
            System.out.println("No hay libros guardados!");
        }
    }

    private void librosSegunIdioma() {
        try {
        System.out.println("Ingresar uno de los idiomas: Ingles[en], Espanol[es]");
        var idioma = teclado.nextLine();
        List<Libro> libros = repositorioLibros.findBooksByLanguage(idioma);
        libros.forEach(libro -> System.out.println(libro));

        } catch (Exception e) {
            System.out.println("No se encuentra ningun libro en ese idioma!");;
        }


    }

    private void topLibrosDescargados() {
        try {
        var libros = repositorioLibros.findTop10BooksByDownloads();
        libros.forEach(libro -> System.out.println(libro));

        } catch (Exception e) {
            System.out.println("No se encuentran libros guardados!");;
        }
    }

    private void listaAutores() {
        try{
        List<String> autores = repositorioAutores.findAllAuthors();
        autores.forEach(autor -> System.out.println(autor));

        } catch (Exception e) {
            System.out.println("No se encontraron autores!");;
        }
    }

    private void autoresVivos() {

        try {
            System.out.println("Ingresa el anio en particular: ");
            var anio = teclado.nextInt();
            var autores = repositorioAutores.findLivingAuthorsByYear(anio);
            autores.forEach(autor -> System.out.println(autor));
        } catch (Exception e) {
            System.out.println("No hay autores vivos en este anio");;
        }
    }

    private void cantidadLibrosIdioma() {
        try {
            System.out.println("Ingresar uno de los idiomas: Ingles[en], Espanol[es]");
            var idioma = teclado.nextLine();
            var libros = repositorioLibros.countBooksByLanguage(idioma);
            System.out.println("Cantidad total ["+idioma + "]: " + libros);
        } catch (Exception e) {
            System.out.println("Algo salio mal!");;
        }
    }


}
