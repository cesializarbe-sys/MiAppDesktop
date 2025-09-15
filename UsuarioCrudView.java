package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.UsuarioController;
import vallegrande.edu.pe.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioCrudView extends JFrame {

    private final UsuarioController controller;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JTextField searchField;

    public UsuarioCrudView(UsuarioController controller) {
        this.controller = controller;

        // Configuración de la ventana
        setTitle("👤 Gestión de Usuarios");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===============================
        // Panel superior (barra de búsqueda)
        // ===============================
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(52, 73, 94)); // Azul oscuro
        JLabel lblBuscar = new JLabel("🔍 Buscar:");
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("SansSerif", Font.BOLD, 14));

        searchField = new JTextField(20);
        JButton btnBuscar = new JButton("Filtrar");
        btnBuscar.setBackground(new Color(41, 128, 185));
        btnBuscar.setForeground(Color.WHITE);

        btnBuscar.addActionListener(e -> filtrarUsuarios());

        searchPanel.add(lblBuscar);
        searchPanel.add(searchField);
        searchPanel.add(btnBuscar);
        add(searchPanel, BorderLayout.NORTH);

        // ===============================
        // Tabla de usuarios
        // ===============================
        tableModel = new DefaultTableModel(new Object[]{"Nombre", "Correo", "Rol"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(41, 128, 185));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ===============================
        // Panel inferior (botones CRUD)
        // ===============================
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(236, 240, 241)); // Gris claro

        JButton addButton = crearBoton("➕ Agregar", new Color(39, 174, 96));    // Verde
        JButton editButton = crearBoton("✏ Editar", new Color(241, 196, 15));   // Amarillo
        JButton deleteButton = crearBoton("🗑 Eliminar", new Color(192, 57, 43));// Rojo

        // Acción Agregar
        addButton.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            String correo = JOptionPane.showInputDialog(this, "Correo:");
            String rol = JOptionPane.showInputDialog(this, "Rol (Administrador/Docente/Estudiante):");
            if (nombre != null && correo != null && rol != null) {
                controller.addUsuario(new Usuario(nombre, correo, rol));
                cargarUsuarios();
            }
        });

        // Acción Editar
        editButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Usuario usuarioExistente = controller.getUsuarios().get(row);
                String nombre = JOptionPane.showInputDialog(this, "Editar nombre:", usuarioExistente.getNombre());
                String correo = JOptionPane.showInputDialog(this, "Editar correo:", usuarioExistente.getCorreo());
                String rol = JOptionPane.showInputDialog(this, "Editar rol:", usuarioExistente.getRol());
                if (nombre != null && correo != null && rol != null) {
                    controller.updateUsuario(row, new Usuario(nombre, correo, rol));
                    cargarUsuarios();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar.");
            }
        });

        // Acción Eliminar
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                controller.deleteUsuario(row);
                cargarUsuarios();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Cargar usuarios iniciales
        cargarUsuarios();
    }

    // Método auxiliar para crear botones con color
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 40));
        return btn;
    }

    // Cargar todos los usuarios
    private void cargarUsuarios() {
        tableModel.setRowCount(0);
        for (Usuario u : controller.getUsuarios()) {
            tableModel.addRow(new Object[]{u.getNombre(), u.getCorreo(), u.getRol()});
        }
    }

    // Filtrar usuarios según búsqueda
    private void filtrarUsuarios() {
        String criterio = searchField.getText();
        List<Usuario> filtrados = controller.buscarUsuarios(criterio);
        tableModel.setRowCount(0);
        for (Usuario u : filtrados) {
            tableModel.addRow(new Object[]{u.getNombre(), u.getCorreo(), u.getRol()});
        }
    }
}