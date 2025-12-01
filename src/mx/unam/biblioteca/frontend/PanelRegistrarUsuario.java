package mx.unam.biblioteca.frontend;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import mx.unam.biblioteca.backend.Usuario;

public class PanelRegistrarUsuario extends JPanel {

    private final BibliotecaGUI ventanaPrincipal;
    private final List<Usuario> listaUsuarios;
    private final JTextField txtNombre;

    public PanelRegistrarUsuario(List<Usuario> listaUsuarios, BibliotecaGUI ventanaPrincipal) {
        this.listaUsuarios = listaUsuarios;
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(new EmptyBorder(10, 10, 10, 10));

        TitledBorder border = BorderFactory.createTitledBorder("Registrar Nuevo Usuario");
        border.setTitleColor(Color.DARK_GRAY);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        setBorder(border);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lbNombre = new JLabel("Nombre completo:");
        txtNombre = new JTextField(25);

        JButton btnGuardar = new JButton("Registrar");
        btnGuardar.addActionListener(e -> registrarUsuario());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        panelFormulario.add(lbNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(btnGuardar, gbc);

        add(panelFormulario, BorderLayout.NORTH);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton regresarBtn = new JButton("Regresar");
        regresarBtn.addActionListener(e -> regresar());
        panelInferior.add(regresarBtn);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);

        listaUsuarios.add(nuevoUsuario);

        JOptionPane.showMessageDialog(this, "Usuario registrado con ID: " + nuevoUsuario.getId());
        txtNombre.setText("");
    }

    public void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }
}