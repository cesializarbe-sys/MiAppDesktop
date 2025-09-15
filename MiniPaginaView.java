package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.EstudianteController;
import vallegrande.edu.pe.controller.UsuarioController;
import javax.swing.*;
import java.awt.*;

public class MiniPaginaView extends JFrame {

    private final UsuarioController controller;

    public MiniPaginaView(UsuarioController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Instituto Valle Grande - Portal Principal");
        setSize(950, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 70, 140));
        header.setPreferredSize(new Dimension(getWidth(), 90));
        JLabel title = new JLabel("Bienvenido al Portal del Instituto Valle Grande");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setBackground(Color.WHITE);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        JLabel infoLabel = new JLabel("<html><center><h2>Gestión Académica y Administrativa</h2>" +
                "Administre usuarios, docentes y estudiantes desde un mismo lugar<br>" +
                "Sistema institucional moderno y seguro</center></html>");
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        infoPanel.add(infoLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(245, 245, 245));

        JButton btnUsuarios = crearBotonMenu("👤 Administrar Usuarios", new Color(0, 120, 215));
        btnUsuarios.addActionListener(e -> new UsuarioCrudView(controller).setVisible(true));

        JButton btnDocentes = crearBotonMenu("📚 Administrar Docentes", new Color(34, 167, 240));
        JButton btnEstudiantes = crearBotonMenu("🎓 Administrar Estudiantes", new Color(34, 153, 84));
        btnEstudiantes.addActionListener(e -> new EstudianteCrudView(new EstudianteController()).setVisible(true));

        menuPanel.add(btnUsuarios);
        menuPanel.add(btnDocentes);
        menuPanel.add(btnEstudiantes);

        centerPanel.add(infoPanel);
        centerPanel.add(menuPanel);
        add(centerPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(230, 230, 230));
        JLabel lblFooter = new JLabel("© 2025 Instituto Valle Grande - Todos los derechos reservados");
        lblFooter.setFont(new Font("SansSerif", Font.ITALIC, 12));
        footer.add(lblFooter);
        add(footer, BorderLayout.SOUTH);
    }

    private JButton crearBotonMenu(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setBackground(colorFondo);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(250, 60));
        return btn;
    }
}