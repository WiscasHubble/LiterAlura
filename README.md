# 📚 **Literalura**

**Literalura** es una aplicación desarrollada en **Java** que permite consultar y gestionar información sobre libros utilizando la API de **Gutendex**. El proyecto permite realizar múltiples consultas, como la búsqueda de libros por título, listado de libros por idioma, obtener el top de libros más descargados, y consultar autores con filtros específicos. Fue creado como parte de un proyecto para aprender a integrar APIs externas, trabajar con bases de datos PostgreSQL y aplicar buenas prácticas en programación con Spring Boot.

## 🚀 **Características principales**

1. **Búsqueda de libro por título**  
    Permite al usuario buscar libros por su título específico, consultando la base de datos de Gutendex.
   
   ![imagen](https://github.com/user-attachments/assets/1d487157-14da-4de0-9d47-5e77a73dac35)

    
2. **Listado de todos los libros buscados**  
    Muestra un listado con los resultados de todas las búsquedas realizadas, facilitando el acceso a la información.
   
   ![imagen](https://github.com/user-attachments/assets/2dcb3d0d-4cfb-463f-99a6-bae49a5ac964)

   
3. **Listado de libros por idioma**  
    Filtra los libros de acuerdo con el idioma seleccionado, permitiendo obtener un listado más específico.
   
   ![imagen](https://github.com/user-attachments/assets/59ee1ea6-ce74-4893-8689-bb428a284922)

    
4. **Top 10 libros más descargados**  
    Muestra el ranking de los 10 libros más descargados según la API de Gutendex, para conocer cuáles son los más populares.
   
     ![imagen](https://github.com/user-attachments/assets/e891fe09-f934-4110-91c8-0fb8926c57ca)

    
5. **Lista de autores**  
    Obtiene una lista de los autores presentes en la base de datos, permitiendo al usuario conocer las personalidades literarias disponibles.
   
   ![imagen](https://github.com/user-attachments/assets/08621f0f-5f29-4179-8c0c-e836bd817fb9)

    
6. **Listar autores vivos en un determinado año**  
    Permite consultar los autores vivos en un año específico, facilitando la búsqueda por fechas de publicación o relevancia.
    
   ![imagen](https://github.com/user-attachments/assets/229da4e9-84fa-47c3-821b-bdc9912047ef)

    
7. **Cantidad de libros en un determinado idioma**  
    Muestra el número de libros disponibles en un idioma específico, dando información cuantitativa sobre el contenido en diferentes idiomas.
    
   ![imagen](https://github.com/user-attachments/assets/f762c269-6cc7-4d17-9400-5d8bfa705c4d)

    

## 📥 **Instalación y configuración**

### **Requisitos previos**

- Java 17 o superior instalado (Puede descargarse desde [aquí](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)).
- **Spring Boot**: Framework principal utilizado para el desarrollo de la aplicación.
- **PostgreSQL**: Base de datos utilizada para almacenar la información.
- **Gutendex API**: API externa para obtener información sobre libros. [Documentación de la API](https://gutendex.com/)

#### **Recomendaciones**

- Instalar [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows).
- Crear un nuevo proyecto **Spring Boot** con **Java 17**.
- Clonar el repositorio desde la raíz del proyecto:

```

git clone https://github.com/TuUsuario/Literalura.git

 ```

#### **Base de Datos**

- Configura tu base de datos PostgreSQL con los parámetros necesarios en `application.properties`.

## 🛠️ **Tecnologías utilizadas**

- [**Java 17**](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html): Lenguaje principal del proyecto.
- [**Spring Boot**](https://spring.io/projects/spring-boot): Framework para crear aplicaciones Java de manera rápida y sencilla.
- [**PostgreSQL**](https://www.postgresql.org/): Sistema de gestión de bases de datos utilizado para almacenar información.
- [**Gutendex API**](https://gutendex.com/): API externa para consultar información sobre libros, autores y más.
- [**Jackson Databind**](https://github.com/FasterXML/jackson): Biblioteca para el manejo de JSON en Java.

## 📦 **Dependencias**

El proyecto utiliza las siguientes dependencias de Maven:
```


<dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-data-jpa</artifactId> </dependency> 

<dependency> <groupId>org.postgresql</groupId> <artifactId>postgresql</artifactId> <scope>runtime</scope> </dependency> 

<dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-test</artifactId> <scope>test</scope> </dependency>

<dependency> <groupId>com.fasterxml.jackson.core</groupId> <artifactId>jackson-databind</artifactId> <version>2.18.2</version> </dependency>


```

## 🤝 **Contribuciones**

¡Contribuciones son bienvenidas! Si deseas colaborar:

1. Haz un fork del repositorio.
2. Crea una rama con tu mejora: `git checkout -b mejora-nueva-funcionalidad`.
3. Realiza un commit: `git commit -m "Añadir nueva funcionalidad"`.
4. Envía un pull request.
