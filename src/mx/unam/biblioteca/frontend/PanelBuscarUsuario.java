package mx.unam.biblioteca.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import mx.unam.biblioteca.backend.Usuario;

public class PanelBuscarUsuario extends JPanel {

    private final BibliotecaGUI ventanaPrincipal;
    private final List<Usuario> listaUsuarios;

    public PanelBuscarUsuario(List<Usuario> listaUsuarios, BibliotecaGUI ventanaPrincipal) {
        this.listaUsuarios = listaUsuarios;
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Buscar usuario");
        border.setTitleColor(Color.BLUE);
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        setBorder(border);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(panelInferior, BorderLayout.SOUTH);

        JButton regresarBtn = new JButton("Regresar");
        regresarBtn.addActionListener(e -> regresar());
        panelInferior.add(regresarBtn);
    }

    public void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }

}
