package mx.unam.biblioteca.backend;

import java.util.List;

public class Usuario {

    private static int contUsuario;
    private String nombre;
    private final int id;
    private List<Libro> librosPrestados;

    public Usuario() {
        this.id = ++contUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public void agregarLibroPrestado(Libro libro) {
        librosPrestados.add(libro);
    }

    @Override
    public String toString() {
        String mostrar = "Id del usuario: "
                + id + "\nNombre del usuario: "
                + nombre + "\nLibros prestados:\n";
        if (librosPrestados != null && !librosPrestados.isEmpty()) {
            for (Libro libro : librosPrestados) {
                mostrar += libro.toString() + "\n";
            }
        } else {
            mostrar += "No hay libros prestados.";
        }
        return mostrar;
    }

}