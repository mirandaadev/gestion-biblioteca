package mx.unam.biblioteca.backend;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private static int contLibro = 0;
    private int id;
    private String titulo;
    private String genero;
    private int anioPublicacion;
    private int copiasTotales; 
    private int copiasDisponibles; 
    private List<Autor> autores;

    public Libro() {
        this.autores = new ArrayList<>();
        this.id = ++contLibro;
        this.copiasTotales = 1; 
        this.copiasDisponibles = 1; 
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
    
  

    public int getCopiasTotales() {
        return copiasTotales;
    }

    public void setCopiasTotales(int copiasTotales) {
        this.copiasTotales = copiasTotales;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }

    
    public boolean isEstado() {
        return copiasDisponibles > 0;
    }
    
   
    public void setEstado(boolean estado) {
        
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
                "\nCopias Disponibles: " + this.getCopiasDisponibles() + // Actualizado
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