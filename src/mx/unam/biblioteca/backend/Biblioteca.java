package mx.unam.biblioteca.backend;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mx.unam.biblioteca.frontend.BibliotecaGUI;

public class Biblioteca {

    private BibliotecaGUI gui;

    public void setGUI(BibliotecaGUI gui) {
        this.gui = gui;
    }

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
        l1.setEstado(true);
        l1.getAutores().add(new Autor("Gabriel", "García Márquez"));
        l1.asignarId();

        Libro l2 = new Libro();
        l2.setTitulo("Buenos presagios");
        l2.setGenero("Fantasía");
        l2.setAnioPublicacion(1990);
        l2.setEstado(true);
        l2.getAutores().add(new Autor("Neil", "Gaiman"));
        l2.getAutores().add(new Autor("Terry", "Pratchett"));
        l2.asignarId();

        Libro l3 = new Libro();
        l3.setTitulo("1984");
        l3.setGenero("Distopía");
        l3.setAnioPublicacion(1949);
        l3.setEstado(true);
        l3.getAutores().add(new Autor("George", "Orwell"));
        l3.asignarId();

        Libro l4 = new Libro();
        l4.setTitulo("El hombre que calculaba");
        l4.setGenero("Aventura / Matemática");
        l4.setAnioPublicacion(1938);
        l4.setEstado(true);
        l4.getAutores().add(new Autor("Malba", "Tahan"));
        l4.getAutores().add(new Autor("Julio", "César de Mello e Souza"));
        l4.asignarId();

        Libro l5 = new Libro();
        l5.setTitulo("Fahrenheit 451");
        l5.setGenero("Ciencia ficción");
        l5.setAnioPublicacion(1953);
        l5.setEstado(true);
        l5.getAutores().add(new Autor("Ray", "Bradbury"));
        l5.asignarId();

        listaLibros.add(l1);
        listaLibros.add(l2);
        listaLibros.add(l3);
        listaLibros.add(l4);
        listaLibros.add(l5);

    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public Libro buscarLibro() {
        if (listaLibros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay libros para buscar");
            return null;
        } else {
            int idLibro = 0;
            do {
                try {
                    idLibro = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro:"));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Formato incorrecto");
                }
                if (idLibro <= 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
                }
            } while (idLibro <= 0);

            for (Libro libro : listaLibros) {
                if (libro.getId() == idLibro) {
                    JOptionPane.showMessageDialog(null, libro.toString());
                    return libro;
                }
            }
        }

        return null;
    }

    public Libro buscarLibroPrestado(int idLibro) {
        for (Libro libro : listaLibros) {
            if (libro.getId() == idLibro) {
                JOptionPane.showMessageDialog(null, libro.toString());
                return libro;
            }

        }
        return null;
    }

    public void prestarLibro() {
        if (listaLibros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay libros registrados");
        } else {
            int idLibro;
            idLibro = Integer.parseInt(JOptionPane.showInputDialog("Id del libro: "));
            for (Libro libro : listaLibros) {
                if (libro.getId() == idLibro) {
                    libro.setEstado(false);
                    listaLibrosPrestados.add(libro);
                    buscarLibroPrestado(idLibro);
                }
            }
        }

    }

    public void devolverLibro() {
        if (listaLibrosPrestados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay libros prestados");
        } else {
            int idLibro;
            idLibro = Integer.parseInt(JOptionPane.showInputDialog("Id del libro: "));
            for (Libro libro : listaLibros) {
                if (libro.getId() == idLibro) {
                    libro.setEstado(true);
                    buscarLibroPrestado(idLibro);
                }
            }
        }
    }

    public void registrarUsuario() {
        Usuario usuario = new Usuario();
        String nombre;
        do {
            nombre = JOptionPane.showInputDialog(null, "Introduzca  el nombre del usuario: ");
            if (nombre == null || nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
            }
        } while (nombre == null || nombre.trim().isEmpty());
        usuario.setNombre(nombre);
        listaUsuarios.add(usuario);
    }

    public void mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios para mostrar");
        } else {
            for (Usuario usuario : listaUsuarios) {
                JOptionPane.showMessageDialog(null,
                        usuario.toString());
            }
        }

    }

    public Usuario buscarUsuario() {
        if (listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios para buscar");
            return null;
        } else {
            int idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el id del usuario:"));
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getId() == idUsuario) {
                    JOptionPane.showMessageDialog(null, "Id: " + usuario.getId() + "\nNombre: " + usuario.getNombre());
                    return usuario;
                }
            }

        }
        return null;
    }

}
