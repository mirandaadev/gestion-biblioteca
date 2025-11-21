package mx.unam.biblioteca.frontend;

import java.awt.*;
import javax.swing.*;
import mx.unam.biblioteca.backend.Biblioteca;

public final class BibliotecaGUI extends JFrame {

    private final Biblioteca biblioteca;

    public BibliotecaGUI(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("BIBLIOTECA TOORBI");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrarMenuPrincipal();
        setVisible(true);
    }

    public void manejarOpcion(int opc) {
        switch (opc) {
            case 1 -> {
                PanelAgregarLibro pal = new PanelAgregarLibro(biblioteca.getListaLibros(), this);
                mostrarPanel(pal);
            }
            case 2 -> {
                PanelMostrarLibros pml = new PanelMostrarLibros(biblioteca.getListaLibros(), this);
                mostrarPanel(pml);
            }
            case 3 -> {
                PanelBuscarLibro pbl = new PanelBuscarLibro(biblioteca.getListaLibros(), this);
                mostrarPanel(pbl);
            }
            case 4 -> {
                PanelPrestarLibro ppl = new PanelPrestarLibro(biblioteca.getListaLibros(),
                        biblioteca.getListaUsuarios(), this);
                mostrarPanel(ppl);
            }
            case 5 -> {
                PanelDevolverLibro pdl = new PanelDevolverLibro(biblioteca.getListaLibros(),
                        biblioteca.getListaUsuarios(), this);
                mostrarPanel(pdl);
            }
            case 6 -> {
                PanelRegistrarUsuario pru = new PanelRegistrarUsuario(biblioteca.getListaUsuarios(), this);
                mostrarPanel(pru);
            }
            case 7 -> {
                PanelMostrarUsuarios pmu = new PanelMostrarUsuarios(biblioteca.getListaUsuarios(), this);
                mostrarPanel(pmu);
            }
            case 8 -> {
                PanelBuscarUsuario pbu = new PanelBuscarUsuario(biblioteca.getListaUsuarios(), this);
                mostrarPanel(pbu);
            }
            case 9 -> {
                JOptionPane.showMessageDialog(null, "Saliendo...");
                System.exit(0);
            }
            default -> JOptionPane.showMessageDialog(null, "Opción inválida");
        }
    }

    public void mostrarMenuPrincipal() {

        getContentPane().removeAll();
        getContentPane().setLayout(new GridLayout(9, 1));

        String[] opciones = {
                "Agregar libro", "Mostrar libros", "Buscar libro", "Prestar libro", "Devolver libro",
                "Registrar usuario", "Mostrar usuario", "Buscar usuarios", "Salir"
        };

        for (int i = 0; i < opciones.length; i++) {
            JButton boton = new JButton(opciones[i]);
            int opcion = i + 1;
            boton.addActionListener(e -> manejarOpcion(opcion));
            getContentPane().add(boton);
        }
        revalidate();
        repaint();
    }

    public void mostrarPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

}