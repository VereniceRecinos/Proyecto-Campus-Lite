package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class DashboardView extends JFrame {

    // MENU
    private JButton btnDashboard;
    private JButton btnStudents;
    private JButton btnCourses;
    private JButton btnReports;
    private JButton btnSettings;

    // HEADER
    private JButton btnTituloApp;
    private JLabel lblUsuario;

    // TABLA
    private JTable tablaLogs;
    private DefaultTableModel modeloTabla;

    public DashboardView() {

        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    /* =========================================
     * CONFIGURACIÓN VENTANA
     * ========================================= */
    private void configurarVentana() {

        setTitle("EduManager Desktop");
        setSize(1365, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    /* =========================================
     * COMPONENTES
     * ========================================= */
    private void inicializarComponentes() {

        /* =========================================
         * HEADER SUPERIOR
         * ========================================= */
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(235, 235, 235));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        btnTituloApp = new JButton("⌂ EduManager Desktop");
        btnTituloApp.setHorizontalAlignment(SwingConstants.LEFT);
        btnTituloApp.setForeground(Color.BLACK);
        btnTituloApp.setFont(new Font("Arial", Font.BOLD, 14));

        btnTituloApp.setBorderPainted(false);
        btnTituloApp.setFocusPainted(false);
        btnTituloApp.setContentAreaFilled(false);
        btnTituloApp.setOpaque(false);
        btnTituloApp.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel panelMenuTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelMenuTop.setOpaque(false);

        panelMenuTop.add(btnTituloApp);
        panelMenuTop.add(new JLabel("File"));
        panelMenuTop.add(new JLabel("Edit"));
        panelMenuTop.add(new JLabel("View"));
        panelMenuTop.add(new JLabel("Tools"));
        panelMenuTop.add(new JLabel("Help"));

        lblUsuario = new JLabel("Admin User (Session: 402)   AU");
        lblUsuario.setFont(new Font("Arial", Font.ITALIC, 13));

        panelHeader.add(panelMenuTop, BorderLayout.WEST);
        panelHeader.add(lblUsuario, BorderLayout.EAST);

        add(panelHeader, BorderLayout.NORTH);

        /* =========================================
         * MENU LATERAL
         * ========================================= */
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(240, 240, 240));
        panelMenu.setPreferredSize(new Dimension(210, 0));
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));

        JLabel lblNav = new JLabel("NAVIGATION");
        lblNav.setFont(new Font("Arial", Font.BOLD, 14));
        lblNav.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 0));

        btnDashboard = crearBotonMenu("▦  Dashboard");
        btnStudents = crearBotonMenu("⚇  Students");
        btnCourses = crearBotonMenu("▣  Courses");
        btnReports = crearBotonMenu("▥  Reports");
        btnSettings = crearBotonMenu("⚙  Settings");

        panelMenu.add(lblNav);
        panelMenu.add(btnDashboard);
        panelMenu.add(btnStudents);
        panelMenu.add(btnCourses);
        panelMenu.add(btnReports);
        panelMenu.add(btnSettings);

        add(panelMenu, BorderLayout.WEST);

        /* =========================================
         * PANEL CENTRAL
         * ========================================= */
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelCentral.setBackground(new Color(245, 245, 245));

        JLabel lblTitulo = new JLabel("Sistema de Control Académico");
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 32));

        JLabel lblSubtitulo = new JLabel("Central administration panel. Ready for input.");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(Color.DARK_GRAY);

        panelCentral.add(lblTitulo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        panelCentral.add(lblSubtitulo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        /* =========================================
         * PANEL ESTADÍSTICAS
         * ========================================= */
        JPanel panelStats = new JPanel(new BorderLayout());
        panelStats.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelStats.setBackground(Color.WHITE);
        panelStats.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));

        JPanel headerStats = new JPanel(new BorderLayout());
        headerStats.setBackground(Color.BLACK);
        headerStats.setPreferredSize(new Dimension(100, 25));

        JLabel lblHeaderStats = new JLabel(" Gestión de Estudiantes");
        lblHeaderStats.setForeground(Color.WHITE);

        headerStats.add(lblHeaderStats, BorderLayout.WEST);

        panelStats.add(headerStats, BorderLayout.NORTH);

        JPanel contenidoStats = new JPanel(new BorderLayout());
        contenidoStats.setBackground(Color.WHITE);
        contenidoStats.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel leftInfo = new JPanel();
        leftInfo.setBackground(Color.WHITE);
        leftInfo.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel lblIcon = new JLabel("👥");
        lblIcon.setFont(new Font("Arial", Font.PLAIN, 50));

        JPanel textInfo = new JPanel();
        textInfo.setBackground(Color.WHITE);
        textInfo.setLayout(new BoxLayout(textInfo, BoxLayout.Y_AXIS));

        JLabel lblRegistry = new JLabel("Registry Statistics");
        lblRegistry.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblUpdate = new JLabel("Update frequency: Real-time");
        lblUpdate.setForeground(Color.DARK_GRAY);

        JPanel panelMiniBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMiniBtns.setBackground(Color.WHITE);

        JButton btnInscripciones = new JButton("Inscripciones");
        JButton btnPerfiles = new JButton("Perfiles");

        panelMiniBtns.add(btnInscripciones);
        panelMiniBtns.add(btnPerfiles);

        textInfo.add(lblRegistry);
        textInfo.add(lblUpdate);
        textInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        textInfo.add(panelMiniBtns);

        leftInfo.add(lblIcon);
        leftInfo.add(textInfo);

        JPanel cards = new JPanel(new GridLayout(1, 3, 10, 0));
        cards.setBackground(Color.WHITE);

        cards.add(crearCard("1,248", "TOTAL ALUMNOS", Color.WHITE));
        cards.add(crearCard("42", "INGRESOS", Color.WHITE));
        cards.add(crearCard("12", "ALERTAS", new Color(255, 220, 220)));

        JPanel contenedorCards = new JPanel(new BorderLayout());
        contenedorCards.setBackground(Color.WHITE);
        contenedorCards.add(cards, BorderLayout.SOUTH);

        contenidoStats.add(leftInfo, BorderLayout.NORTH);
        contenidoStats.add(contenedorCards, BorderLayout.CENTER);

        panelStats.add(contenidoStats, BorderLayout.CENTER);

        panelCentral.add(panelStats);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        /* =========================================
         * LOGS
         * ========================================= */
        JPanel panelLogs = new JPanel(new BorderLayout());
        panelLogs.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelLogs.setBackground(Color.WHITE);

        JPanel logsHeader = new JPanel(new BorderLayout());
        logsHeader.setBackground(new Color(80, 90, 105));
        logsHeader.setPreferredSize(new Dimension(100, 35));

        JLabel lblLogs = new JLabel(" Activity Log / Event Monitor");
        lblLogs.setForeground(Color.WHITE);
        lblLogs.setFont(new Font("Arial", Font.BOLD, 14));

        logsHeader.add(lblLogs, BorderLayout.WEST);

        panelLogs.add(logsHeader, BorderLayout.NORTH);

        String[] columnas = {"T", "Description", "Timestamp"};

        modeloTabla = new DefaultTableModel(columnas, 0);

        tablaLogs = new JTable(modeloTabla);
        tablaLogs.setRowHeight(30);

        modeloTabla.addRow(new Object[]{
            "ⓘ",
            "Calificaciones Matemáticas II publicadas.",
            "Hace 2 horas"
        });

        modeloTabla.addRow(new Object[]{
            "✓",
            "Nuevo estudiante inscrito: García, Roberto.",
            "09:15 AM"
        });

        modeloTabla.addRow(new Object[]{
            "⚠",
            "Backup completed with 2 warnings.",
            "Ayer 11:45 PM"
        });

        JScrollPane scrollLogs = new JScrollPane(tablaLogs);

        panelLogs.add(scrollLogs, BorderLayout.CENTER);

        panelCentral.add(panelLogs);

        add(panelCentral, BorderLayout.CENTER);
    }

    /* =========================================
     * BOTÓN MENU
     * ========================================= */
    private JButton crearBotonMenu(String texto) {

        JButton boton = new JButton(texto);

        boton.setMaximumSize(new Dimension(180, 40));
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.PLAIN, 14));

        return boton;
    }

    /* =========================================
     * CARD
     * ========================================= */
    private JPanel crearCard(String numero, String texto, Color color) {

        JPanel card = new JPanel();
        card.setBackground(color);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel lblNumero = new JLabel(numero);
        lblNumero.setAlignmentX(CENTER_ALIGNMENT);
        lblNumero.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel lblTexto = new JLabel(texto);
        lblTexto.setAlignmentX(CENTER_ALIGNMENT);
        lblTexto.setFont(new Font("Arial", Font.BOLD, 12));

        card.add(Box.createVerticalGlue());
        card.add(lblNumero);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(lblTexto);
        card.add(Box.createVerticalGlue());

        return card;
    }

    /* =========================================
     * EVENTOS
     * ========================================= */
    private void agregarEventos() {

        btnDashboard.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Dashboard seleccionado."
            ); 
        });

        btnStudents.addActionListener (e -> {
            
            PantallaEstudiantes ventana = new PantallaEstudiantes();
            ventana.setVisible(true);
            
            dispose();
        });

        btnCourses.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Módulo cursos."
            );
        });

        btnReports.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Módulo reportes."
            );
        });

        btnSettings.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Configuraciones del sistema."
            );
        });

        btnTituloApp.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "EduManager Desktop"
            );
        });
    }

    /* =========================================
     * MAIN
     * ========================================= */
    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {

            new DashboardView().setVisible(true);
        });
    }
}
