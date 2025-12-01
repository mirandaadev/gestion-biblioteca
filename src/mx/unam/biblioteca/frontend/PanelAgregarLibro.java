package mx.unam.biblioteca.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import mx.unam.biblioteca.backend.Autor;
import mx.unam.biblioteca.backend.Libro;

public class PanelAgregarLibro extends JPanel {

    private final JTextField txtTitulo;
    private final JTextField txtGenero;
    private final JTextField txtAnio;
    private final JTextField txtNumAutores;

    private final JLabel lbTitulo;
    private final JLabel lbGenero;
    private final JLabel lbAnio;
    private final JLabel lbNumAutores;
    private final JPanel autoresPanel;
    private final List<Autor> autores;
    private final List<JTextField[]> camposAutores;
    private final List<Libro> listaLibros;
    private final BibliotecaGUI ventanaPrincipal;

    public PanelAgregarLibro(List<Libro> listaLibros, BibliotecaGUI ventanaPrincipal) {
        this.listaLibros = listaLibros;
        this.ventanaPrincipal = ventanaPrincipal;
        this.autoresPanel = new JPanel(new GridBagLayout());

        autores = new ArrayList<>();
        camposAutores = new ArrayList<>();
        setLayout(new BorderLayout());

        JPanel panelAgregar = new JPanel(new GridBagLayout());
        panelAgregar.setBorder(new EmptyBorder(10, 10, 10, 10));

        TitledBorder border = BorderFactory.createTitledBorder("Agregar libro");
        border.setTitleFont(new Font("Arial", Font.BOLD, 16));
        border.setTitleColor(Color.DARK_GRAY);
        border.setTitleJustification(TitledBorder.LEFT);
        setBorder(border);

        TitledBorder borderAutores = BorderFactory.createTitledBorder("Autores del Libro");
        autoresPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        borderAutores.setTitleFont(new Font("Arial", Font.BOLD, 16));
        borderAutores.setTitleColor(Color.DARK_GRAY);
        borderAutores.setTitleJustification(TitledBorder.LEFT);
        this.autoresPanel.setBorder(borderAutores);

        lbTitulo = new JLabel("Titulo: ");
        lbGenero = new JLabel("Género: ");
        lbAnio = new JLabel("Año de publicación: ");
        lbNumAutores = new JLabel("Número de autores: ");

        txtTitulo = new JTextField(30);
        txtTitulo.setEditable(true);
        txtTitulo.setBackground(Color.WHITE);
        txtTitulo.setForeground(Color.BLUE);

        txtGenero = new JTextField(30);
        txtGenero.setEditable(true);
        txtGenero.setBackground(Color.WHITE);
        txtGenero.setForeground(Color.BLUE);

        txtAnio = new JTextField(30);
        txtAnio.setEditable(true);
        txtAnio.setBackground(Color.WHITE);
        txtAnio.setForeground(Color.BLUE);

        txtNumAutores = new JTextField(30);
        txtNumAutores.setEditable(true);
        txtNumAutores.setBackground(Color.WHITE);
        txtNumAutores.setForeground(Color.BLACK);

        JButton agregarAutorBtn = new JButton("Agregar Autor / Autores");
        agregarAutorBtn.addActionListener(e -> agregarAutores());

        GridBagConstraints gbcLibro = new GridBagConstraints();
        gbcLibro.insets = new Insets(5, 5, 5, 5);

        gbcLibro.gridx = 0;
        gbcLibro.gridy = 0;
        gbcLibro.anchor = GridBagConstraints.WEST;
        gbcLibro.weightx = 0;
        panelAgregar.add(lbTitulo, gbcLibro);

        gbcLibro.gridx = 1;
        gbcLibro.gridy = 0;
        gbcLibro.fill = GridBagConstraints.HORIZONTAL;
        gbcLibro.weightx = 1.0;
        panelAgregar.add(txtTitulo, gbcLibro);

        gbcLibro.gridx = 0;
        gbcLibro.gridy = 1;
        gbcLibro.fill = GridBagConstraints.NONE;
        gbcLibro.weightx = 0;
        panelAgregar.add(lbGenero, gbcLibro);

        gbcLibro.gridx = 1;
        gbcLibro.gridy = 1;
        gbcLibro.fill = GridBagConstraints.HORIZONTAL;
        gbcLibro.weightx = 1.0;
        panelAgregar.add(txtGenero, gbcLibro);

        gbcLibro.gridx = 0;
        gbcLibro.gridy = 2;
        gbcLibro.fill = GridBagConstraints.NONE;
        gbcLibro.weightx = 0;
        panelAgregar.add(lbAnio, gbcLibro);

        gbcLibro.gridx = 1;
        gbcLibro.gridy = 2;
        gbcLibro.fill = GridBagConstraints.HORIZONTAL;
        gbcLibro.weightx = 1.0;
        panelAgregar.add(txtAnio, gbcLibro);

        gbcLibro.gridx = 0;
        gbcLibro.gridy = 3;
        gbcLibro.fill = GridBagConstraints.NONE;
        gbcLibro.weightx = 0;
        panelAgregar.add(lbNumAutores, gbcLibro);

        gbcLibro.gridx = 1;
        gbcLibro.gridy = 3;
        gbcLibro.fill = GridBagConstraints.HORIZONTAL;
        gbcLibro.weightx = 1.0;
        panelAgregar.add(txtNumAutores, gbcLibro);

        gbcLibro.gridx = 1;
        gbcLibro.gridy = 4;
        gbcLibro.anchor = GridBagConstraints.WEST;
        gbcLibro.weightx = 0;
        panelAgregar.add(agregarAutorBtn, gbcLibro);

        add(panelAgregar, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(this.autoresPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        JButton guardarLibroBtn = new JButton("Guardar Libro");
        guardarLibroBtn.addActionListener(e -> guardarLibro());

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelInferior.add(guardarLibroBtn);

        JButton regresarBtn = new JButton("Regresar");
        regresarBtn.addActionListener(e -> regresar());
        panelInferior.add(regresarBtn);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void agregarAutores() {
        autoresPanel.removeAll();
        autores.clear();
        camposAutores.clear();

        int numAutores;
        try {
            numAutores = Integer.parseInt(txtNumAutores.getText().trim());
            if (numAutores <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido de autores.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        GridBagConstraints gbcAutores = new GridBagConstraints();
        gbcAutores.insets = new Insets(5, 5, 5, 5);
        gbcAutores.gridx = 0;
        gbcAutores.fill = GridBagConstraints.HORIZONTAL;
        gbcAutores.weightx = 1.0;

        for (int i = 0; i < numAutores; i++) {

            gbcAutores.gridy = i;

            JLabel lbNombre = new JLabel("Nombre:");
            JLabel lbApellidos = new JLabel("Apellidos:");
            JLabel lbNacionalidad = new JLabel("Nacionalidad:");

            JTextField txtNombre = new JTextField(15);
            JTextField txtApellidos = new JTextField(15);
            JTextField txtNacionalidad = new JTextField(15);

            JPanel autorPanel = new JPanel(new GridBagLayout());
            autorPanel.setBorder(BorderFactory.createTitledBorder("Autor " + (i + 1)));

            GridBagConstraints gbcSub = new GridBagConstraints();
            gbcSub.insets = new Insets(2, 5, 2, 5);
            gbcSub.anchor = GridBagConstraints.WEST;

            gbcSub.gridx = 0;
            gbcSub.gridy = 0;
            gbcSub.weightx = 0;
            gbcSub.fill = GridBagConstraints.NONE;
            autorPanel.add(lbNombre, gbcSub);

            gbcSub.gridx = 1;
            gbcSub.gridy = 0;
            gbcSub.weightx = 1.0;
            gbcSub.fill = GridBagConstraints.HORIZONTAL;
            autorPanel.add(txtNombre, gbcSub);

            gbcSub.gridx = 0;
            gbcSub.gridy = 1;
            gbcSub.weightx = 0;
            gbcSub.fill = GridBagConstraints.NONE;
            autorPanel.add(lbApellidos, gbcSub);

            gbcSub.gridx = 1;
            gbcSub.gridy = 1;
            gbcSub.weightx = 1.0;
            gbcSub.fill = GridBagConstraints.HORIZONTAL;
            autorPanel.add(txtApellidos, gbcSub);

            gbcSub.gridx = 0;
            gbcSub.gridy = 2;
            gbcSub.weightx = 0;
            gbcSub.fill = GridBagConstraints.NONE;
            autorPanel.add(lbNacionalidad, gbcSub);

            gbcSub.gridx = 1;
            gbcSub.gridy = 2;
            gbcSub.weightx = 1.0;
            gbcSub.fill = GridBagConstraints.HORIZONTAL;
            autorPanel.add(txtNacionalidad, gbcSub);

            autoresPanel.add(autorPanel, gbcAutores);

            camposAutores.add(new JTextField[] { txtNombre, txtApellidos, txtNacionalidad });
        }

        JButton guardarBtn = new JButton("Guardar Autores");
        guardarBtn.addActionListener(e -> {
            autores.clear();
            for (JTextField[] campos : camposAutores) {
                String nombre = campos[0].getText();
                String apellidos = campos[1].getText();
                String nacionalidad = campos[2].getText();

                if (nombre.isEmpty() || apellidos.isEmpty() || nacionalidad.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos de autor deben estar llenos.");
                    autores.clear();
                    return;
                }

                Autor autor = new Autor();
                autor.setNombre(nombre);
                autor.setApellidos(apellidos);
                autores.add(autor);
            }
            JOptionPane.showMessageDialog(this, "Autores agregados correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        gbcAutores.gridy = numAutores;
        gbcAutores.fill = GridBagConstraints.NONE;
        gbcAutores.weightx = 0;
        gbcAutores.anchor = GridBagConstraints.WEST;
        autoresPanel.add(guardarBtn, gbcAutores);

        gbcAutores.gridy = numAutores + 1;
        gbcAutores.weighty = 1.0;
        gbcAutores.fill = GridBagConstraints.VERTICAL;
        autoresPanel.add(new JPanel(), gbcAutores);

        autoresPanel.revalidate();
        autoresPanel.repaint();

        Window window = SwingUtilities.getWindowAncestor(this);

        if (window != null) {
            window.pack();
        }
    }

    public Libro obtenerLibro() {

        String titulo = txtTitulo.getText().trim();
        String genero = txtGenero.getText().trim();
        String anioTexto = txtAnio.getText().trim();

        if (titulo.isEmpty() || genero.isEmpty() || anioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos deben estar llenos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        int anio;
        try {
            anio = Integer.parseInt(anioTexto);
            if (anio <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El año debe ser un número válido y positivo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (autores.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe agregar al menos un autor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setGenero(genero);
        libro.setAnioPublicacion(anio);
        libro.setAutores(autores);

        libro.setAutores(new ArrayList<>(autores));

        return libro;
    }

    public void limpiarVentana() {
        txtTitulo.setText("");
        txtGenero.setText("");
        txtAnio.setText("");
        txtNumAutores.setText("");
        autoresPanel.removeAll();
        autoresPanel.revalidate();
        autoresPanel.repaint();
        autores.clear();
        camposAutores.clear();
    }

    public void guardarLibro() {
        Libro libro = obtenerLibro();
        if (libro != null) {
            listaLibros.add(libro);
            JOptionPane.showMessageDialog(null, "Libro " + libro.getTitulo() + " agregado correctamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        limpiarVentana();
    }

    public void regresar() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.mostrarMenuPrincipal();
        }
    }

}