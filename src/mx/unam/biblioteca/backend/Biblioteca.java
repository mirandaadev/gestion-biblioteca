package mx.unam.biblioteca.backend;

import java.util.ArrayList;
import java.util.List;
import mx.unam.biblioteca.frontend.BibliotecaGUI;

public class Biblioteca {

    private BibliotecaGUI gui;

    private final List<Libro> listaLibros;
    private final List<Libro> listaLibrosPrestados;
    private final List<Usuario> listaUsuarios;

    public Biblioteca() {

        this.listaLibros = new ArrayList<>();
        this.listaLibrosPrestados = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();

        Libro l1 = new Libro();
        l1.setTitulo("Cien años de soledad");
        l1.setGenero("Realismo mágico");
        l1.setAnioPublicacion(1967);
        l1.setCopiasTotales(3); // Ejemplo: 3 copias totales
        l1.setCopiasDisponibles(3); // 3 disponibles
        l1.getAutores().add(new Autor("Gabriel", "García Márquez"));
        l1.asignarId();

        Libro l2 = new Libro();
        l2.setTitulo("Buenos presagios");
        l2.setGenero("Fantasía");
        l2.setAnioPublicacion(1990);
        l2.setCopiasTotales(2); 
        l2.setCopiasDisponibles(2); 
        l2.getAutores().add(new Autor("Neil", "Gaiman"));
        l2.getAutores().add(new Autor("Terry", "Pratchett"));
        l2.asignarId();

        Libro l3 = new Libro();
        l3.setTitulo("1984");
        l3.setGenero("Distopía");
        l3.setAnioPublicacion(1949);
        l3.setCopiasTotales(1); 
        l3.setCopiasDisponibles(1); 
        l3.getAutores().add(new Autor("George", "Orwell"));
        l3.asignarId();

        Libro l4 = new Libro();
        l4.setTitulo("El hombre que calculaba");
        l4.setGenero("Aventura / Matemática");
        l4.setAnioPublicacion(1938);
        l4.setCopiasTotales(4); 
        l4.setCopiasDisponibles(4); 
        l4.getAutores().add(new Autor("Malba", "Tahan"));
        l4.getAutores().add(new Autor("Julio", "César de Mello e Souza"));
        l4.asignarId();

        Libro l5 = new Libro();
        l5.setTitulo("Fahrenheit 451");
        l5.setGenero("Ciencia ficción");
        l5.setAnioPublicacion(1953);
        l5.setCopiasTotales(2); 
        l5.setCopiasDisponibles(2); 
        l5.getAutores().add(new Autor("Ray", "Bradbury"));
        l5.asignarId();

        listaLibros.add(l1);
        listaLibros.add(l2);
        listaLibros.add(l3);
        listaLibros.add(l4);
        listaLibros.add(l5);

    }

    public void setGUI(BibliotecaGUI gui) {
        this.gui = gui;
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

}