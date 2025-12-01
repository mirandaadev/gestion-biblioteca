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
import javax.swing.border.TitledBorder;
import mx.unam.biblioteca.backend.Libro;
import mx.unam.biblioteca.backend.Usuario;

public class PanelDevolverLibro extends JPanel {

    private final BibliotecaGUI ventanaPrincipal;
    private final List<Libro> listaLibros;
    private final List<Usuario> listaUsuarios;

    private final JLabel lbIdLibro;
    private final JTextField txtIdLibro;
    private final JLabel lbIdUsuario;
    private final JTextField txtIdUsuario;

    public PanelDevolverLibro(List<Libro> listaLibros, List<Usuario> listaUsuarios, BibliotecaGUI ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.listaLibros = listaLibros;
        this.listaUsuarios = listaUsuarios;

        setLayout(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Devolver libro");
        border.setTitleColor(Color.DARK_GRAY);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        setBorder(border);

        JPanel panelPrestarLibro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        lbIdLibro = new JLabel("ID Libro:");
        txtIdLibro = new JTextField(30);

        lbIdUsuario = new JLabel("ID Usuario: ");
        txtIdUsuario = new JTextField(30);

        JButton buscarbtn = new JButton("Devolver");
        buscarbtn.addActionListener(e -> devolverLibro());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        panelPrestarLibro.add(lbIdLibro, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelPrestarLibro.add(txtIdLibro, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        panelPrestarLibro.add(lbIdUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelPrestarLibro.add(txtIdUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelPrestarLibro.add(buscarbtn, gbc);

        add(panelPrestarLibro, BorderLayout.NORTH);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton regresarBtn = new JButton("Regresar");
        regresarBtn.addActionListener(e -> regresar());
        panelInferior.add(regresarBtn);

        add(panelInferior, BorderLayout.SOUTH);

    }

    private void devolverLibro() {
        String strIdLibro = txtIdLibro.getText().trim();
        String strIdUsuario = txtIdUsuario.getText().trim();

        if (strIdLibro.isEmpty() || strIdUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el ID del Libro y del Usuario.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idLibro = Integer.parseInt(strIdLibro);
            int idUsuario = Integer.parseInt(strIdUsuario);

            Libro libro = null;
            for (Libro l : listaLibros) {
                if (l.getId() == idLibro) {
                    libro = l;
                    break;
                }
            }

            Usuario usuario = null;
            for (Usuario u : listaUsuarios) {
                if (u.getId() == idUsuario) {
                    usuario = u;
                    break;
                }
            }

            if (libro == null || usuario == null) {
                JOptionPane.showMessageDialog(this, "Libro o Usuario no encontrado.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean removido = usuario.getLibrosPrestados().removeIf(l -> l.getId() == idLibro);

            if (removido) {

                libro.setCopiasDisponibles(libro.getCopiasDisponibles() + 1);

                JOptionPane.showMessageDialog(this,
                        "Devolución exitosa. Libro '" + libro.getTitulo() + "' devuelto por " + usuario.getNombre(),
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtIdLibro.setText("");
                txtIdUsuario.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "El usuario no tiene prestado el Libro ID: " + libro.getId(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los ID deben ser números enteros.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }
}
