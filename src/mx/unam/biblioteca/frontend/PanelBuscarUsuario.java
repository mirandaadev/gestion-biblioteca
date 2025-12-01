package mx.unam.biblioteca.frontend;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import mx.unam.biblioteca.backend.Libro;
import mx.unam.biblioteca.backend.Usuario;

public class PanelBuscarUsuario extends JPanel {

    private final BibliotecaGUI ventanaPrincipal;
    private final List<Usuario> listaUsuarios;
    private final JTextField txtID;
    private final JPanel panelInfoUsuario;

    public PanelBuscarUsuario(List<Usuario> listaUsuarios, BibliotecaGUI ventanaPrincipal) {
        this.listaUsuarios = listaUsuarios;
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Buscar Usuario");
        border.setTitleColor(Color.BLUE);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        setBorder(border);

        
        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.setBorder(new EmptyBorder(10, 10, 0, 10));
        
        JLabel lbID = new JLabel("ID de Usuario: ");
        txtID = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarUsuario());

        panelBuscar.add(lbID);
        panelBuscar.add(txtID);
        panelBuscar.add(btnBuscar);

        add(panelBuscar, BorderLayout.NORTH);

        
        panelInfoUsuario = new JPanel(new GridBagLayout());
        panelInfoUsuario.setBorder(BorderFactory.createTitledBorder("Datos del Usuario Encontrado"));
        add(panelInfoUsuario, BorderLayout.CENTER);

        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JButton regresarBtn = new JButton("Regresar");
        regresarBtn.addActionListener(e -> regresar());
        panelInferior.add(regresarBtn);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void buscarUsuario() {
        String textoId = txtID.getText().trim();

        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idBuscado;
        try {
            idBuscado = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuarioEncontrado = null;
        for (Usuario u : listaUsuarios) {
            if (u.getId() == idBuscado) {
                usuarioEncontrado = u;
                break;
            }
        }

        actualizarPanelResultados(usuarioEncontrado);
    }

    private void actualizarPanelResultados(Usuario usuario) {
        panelInfoUsuario.removeAll();
        
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            panelInfoUsuario.revalidate();
            panelInfoUsuario.repaint();
            return;
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; 
        
        
        gbc.gridy = 0;
        panelInfoUsuario.add(new JLabel("ID: " + usuario.getId()), gbc);

        
        gbc.gridy = 1;
        JLabel lblNombre = new JLabel("Nombre: " + usuario.getNombre());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        panelInfoUsuario.add(lblNombre, gbc);

        
        gbc.gridy = 2;
        String librosTexto = "Sin libros prestados";
        if (usuario.getLibrosPrestados() != null && !usuario.getLibrosPrestados().isEmpty()) {
            librosTexto = usuario.getLibrosPrestados().stream()
                    .map(Libro::getTitulo)
                    .collect(Collectors.joining(", "));
        }
        
        
        JTextArea areaLibros = new JTextArea("Libros prestados: " + librosTexto);
        areaLibros.setLineWrap(true);
        areaLibros.setWrapStyleWord(true);
        areaLibros.setEditable(false);
        areaLibros.setBackground(panelInfoUsuario.getBackground());
        areaLibros.setPreferredSize(new Dimension(300, 60)); 
        
        panelInfoUsuario.add(areaLibros, gbc);

        panelInfoUsuario.revalidate();
        panelInfoUsuario.repaint();
    }

    public void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }
}
