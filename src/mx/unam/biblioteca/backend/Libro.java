package mx.unam.biblioteca.backend;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private static int contLibro = 0;
    private int id;
    private String titulo;
    private String genero;
    private int anioPublicacion;
    private boolean estado;
    private List<Autor> autores;

    public Libro() {
        this.autores = new ArrayList<>();
        this.id = ++contLibro;
    }

    public void asignarId() {
        if (this.id == 0) {
            this.id = ++contLibro;
        }
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public void eliminarAutor() {
        autores.removeFirst();

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        String mostrar = "Id: " + this.getId() +
                "\nTítulo: " + (this.getTitulo() == null ? "" : this.getTitulo()) +
                "\nGénero: " + (this.getGenero() == null ? "" : this.getGenero()) +
                "\nAño de Publicación: " + this.getAnioPublicacion() +
                "\nEstado: " + (this.isEstado() ? "Disponible" : "No disponible") +
                "\n";

        if (this.getAutores() != null && !this.getAutores().isEmpty()) {
            mostrar += "Autores:\n";
            for (Autor autor : this.getAutores()) {
                mostrar += autor.toString() + "\n";
            }
        }
        return mostrar;
    }

}
