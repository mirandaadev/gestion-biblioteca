package mx.unam.biblioteca.frontend;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import mx.unam.biblioteca.backend.Libro;

public class PanelMostrarLibros extends JPanel {

    private final BibliotecaGUI ventanaPrincipal;
    private final List<Libro> listaLibros;
    private final JTable tabla;
    private final DefaultTableModel modeloTabla;

    public PanelMostrarLibros(List<Libro> listaLibros, BibliotecaGUI ventanaPrincipal) {
        this.listaLibros = listaLibros;
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Catálogo de Libros");
        border.setTitleColor(Color.BLUE);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        setBorder(border);

        String[] columnas = { "ID", "Título", "Género", "Año", "Autores", "Disponible" };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(25);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(225);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200);

        cargarDatos();

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRegresar = new JButton("Regresar");

        btnRegresar.addActionListener(e -> regresar());

        panelInferior.add(btnRegresar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarDatos() {

        for (Libro libro : listaLibros) {

            String autoresString = libro.getAutores().stream()
                    .map(a -> a.getNombre()+ " " + a.getApellidos())
                    .collect(Collectors.joining(", "));

            if (autoresString.isEmpty())
                autoresString = "Sin autores";

            Object[] fila = {
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getGenero(),
                    libro.getAnioPublicacion(),
                    autoresString,
                    libro.isEstado() ? "Sí" : "Prestado"
            };

            modeloTabla.addRow(fila);
        }
    }

    private void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }
}