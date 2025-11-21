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
        TitledBorder border = BorderFactory.createTitledBorder("Prestar libro:");
        border.setTitleColor(Color.BLUE);
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
        String idLibro = txtIdLibro.getText();
        String idUsuario = txtIdUsuario.getText();

        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idLibro.isEmpty() || idUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idLibroE;
        int idUsuarioE;
        try {
            idLibroE = Integer.parseInt(idLibro);
            idUsuarioE = Integer.parseInt(idUsuario);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los IDs deben ser números válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Libro libroPrestado = null;

        for (Libro libro : listaLibros) {
            if (libro.getId() == idLibroE) {
                libro.setEstado(true);
                libroPrestado = libro;
                System.out.println("Libro encontrado y prestado: " + libro.getTitulo());
                break;
            }
        }

        if (libroPrestado == null) {
            JOptionPane.showMessageDialog(null, "El libro con el ID especificado no existe", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuarioConLibro = null;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == idUsuarioE) {
                usuarioConLibro = usuario;
                System.out.println("Usuario encontrado: " + usuario.getNombre());
                break;
            }
        }

        if (usuarioConLibro == null) {
            JOptionPane.showMessageDialog(null, "El usuario con el ID especificado no existe", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "El libro '" + libroPrestado.getTitulo()
                + "' ha sido prestado exitosamente a " + usuarioConLibro.getNombre(), "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }
}
