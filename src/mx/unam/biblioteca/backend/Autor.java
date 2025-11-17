package mx.unam.biblioteca.backend;

public class Autor {
    private String nombre;
    private String apellidos;

    public Autor() {

    }

    public Autor(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {

        return " - " + nombre + " " + apellidos;
    }
}