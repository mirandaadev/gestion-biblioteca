package mx.unam.biblioteca.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import mx.unam.biblioteca.backend.Libro;

public class PanelBuscarLibro extends JPanel {

    private final BibliotecaGUI ventanaPrincipal;
    private final List<Libro> listaLibros;
    private final JLabel lbID;
    private final JTextField txtID;
    private final JPanel panelLibroEncontrado;

    public PanelBuscarLibro(List<Libro> listaLibros, BibliotecaGUI ventanaPrincipal) {
        this.listaLibros = listaLibros;
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Buscar libro");
        border.setTitleColor(Color.DARK_GRAY);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        setBorder(border);

        JPanel panelBuscar = new JPanel(new GridBagLayout());
        panelBuscar.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        lbID = new JLabel("ID: ");
        txtID = new JTextField(30);
        JButton buscarLibroBtn = new JButton("Buscar libro");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0;
        panelBuscar.add(lbID, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        panelBuscar.add(txtID, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelBuscar.add(buscarLibroBtn, gbc);

        buscarLibroBtn.addActionListener(e -> buscarLibro());

        add(panelBuscar, BorderLayout.NORTH);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(panelInferior, BorderLayout.SOUTH);

        JButton regresarBtn = new JButton("Regresar");
        regresarBtn.addActionListener(e -> regresar());
        panelInferior.add(regresarBtn);

        panelLibroEncontrado = new JPanel(new GridBagLayout());
        panelBuscar.setBorder(new EmptyBorder(10, 10, 10, 10));

        TitledBorder borderLibroEncontrado = BorderFactory.createTitledBorder("Datos del libro");
        borderLibroEncontrado.setTitleFont(new Font("Arial", Font.BOLD, 16));
        borderLibroEncontrado.setTitleColor(Color.DARK_GRAY);
        borderLibroEncontrado.setTitleJustification(TitledBorder.CENTER);
        panelLibroEncontrado.setBorder(borderLibroEncontrado);

        add(panelLibroEncontrado, BorderLayout.CENTER);

    }

    private void buscarLibro() {
        String idLibro = txtID.getText().trim();

        if (idLibro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idLibro);
            if (id <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número válido y positivo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Libro libroEncontrado = null;
        for (Libro libro : listaLibros) {
            if (id == libro.getId()) {
                libroEncontrado = libro;
                break;
            }
        }

        panelLibroEncontrado.removeAll();

        if (libroEncontrado != null) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            JLabel lbIdLocal = new JLabel("ID: " + libroEncontrado.getId());
            JLabel lbTitulo = new JLabel("Título: " + libroEncontrado.getTitulo());
            JLabel lbGenero = new JLabel("Género: " + libroEncontrado.getGenero());
            JLabel lbAnio = new JLabel("Año de publicación: " + libroEncontrado.getAnioPublicacion());
            JLabel lbAutores = new JLabel("Autores: " + libroEncontrado.getAutores());

            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            panelLibroEncontrado.add(lbIdLocal, gbc);
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            panelLibroEncontrado.add(lbTitulo, gbc);
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            panelLibroEncontrado.add(lbGenero, gbc);
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            panelLibroEncontrado.add(lbAnio, gbc);
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            panelLibroEncontrado.add(lbAutores, gbc);

        } else {
            JOptionPane.showMessageDialog(null, "No se encontro algún libro", "Error", JOptionPane.ERROR_MESSAGE);
        }

        panelLibroEncontrado.revalidate();
        panelLibroEncontrado.repaint();
    }

    public void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }
}
