package mx.unam.biblioteca.frontend;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import mx.unam.biblioteca.backend.Usuario;

public class PanelMostrarUsuarios extends JPanel {

    private final BibliotecaGUI ventanaPrincipal;
    private final List<Usuario> listaUsuarios;
    private final JTable tabla;
    private final DefaultTableModel modeloTabla;

    public PanelMostrarUsuarios(List<Usuario> listaUsuarios, BibliotecaGUI ventanaPrincipal) {
        this.listaUsuarios = listaUsuarios;
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Directorio de Usuarios");
        border.setTitleColor(Color.BLUE);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        setBorder(border);

        
        String[] columnas = { "ID", "Nombre", "Libros Prestados" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        
        
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50); // ID pequeño
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre grande

        cargarDatos();

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton regresarBtn = new JButton("Regresar");
        regresarBtn.addActionListener(e -> regresar());
        panelInferior.add(regresarBtn);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        for (Usuario u : listaUsuarios) {
            int cantidadLibros = 0;
            if (u.getLibrosPrestados() != null) {
                cantidadLibros = u.getLibrosPrestados().size();
            }

            Object[] fila = {
                u.getId(),
                u.getNombre(),
                cantidadLibros 
            };
            modeloTabla.addRow(fila);
        }
    }

    public void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }
}