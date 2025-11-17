package mx.unam.biblioteca.frontend;

import java.awt.*;
import javax.swing.*;
import mx.unam.biblioteca.backend.Biblioteca;

public final class BibliotecaGUI extends JFrame {

    private final transient Biblioteca biblioteca;

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
                PanelAgregarLibro panelAgregarLibro = new PanelAgregarLibro(biblioteca.getListaLibros(), this);
                mostrarPanel(panelAgregarLibro);
            }
            case 2 -> {
                PanelMostrarLibros panelMostrarLibros = new PanelMostrarLibros(biblioteca.getListaLibros(), this);
                mostrarPanel(panelMostrarLibros);
            }
            case 3 -> biblioteca.buscarLibro();
            case 4 -> biblioteca.prestarLibro();
            case 5 -> biblioteca.devolverLibro();
            case 6 -> biblioteca.registrarUsuario();
            case 7 -> biblioteca.mostrarUsuarios();
            case 8 -> biblioteca.buscarUsuario();
            case 9 -> {
                JOptionPane.showMessageDialog(null, "Saliendo...");
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
        pack();
        revalidate();
        repaint();
    }

}