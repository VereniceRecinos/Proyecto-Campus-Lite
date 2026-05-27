package view;

import view.EduManagerFrame;
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
	private JButton btnStudents;
	private JButton btnCourses;
	private JButton btnEvaluaciones;
	private JButton btnInscripciones;
	private JButton btnSalir;

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
    	JPanel panelHeader = new JPanel();
    	panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
    	panelHeader.setBackground(new Color(10, 60, 80));
    	panelHeader.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

    	JLabel lblTituloSistema = new JLabel("Campus Lite");
    	lblTituloSistema.setForeground(Color.WHITE);
    	lblTituloSistema.setFont(new Font("Arial", Font.BOLD, 30));

    	JLabel lblSubtituloSistema = new JLabel("Sistema Académico");
    	lblSubtituloSistema.setForeground(Color.WHITE);
    	lblSubtituloSistema.setFont(new Font("Arial", Font.PLAIN, 16));

    	panelHeader.add(lblTituloSistema);
    	panelHeader.add(lblSubtituloSistema);

    	add(panelHeader, BorderLayout.NORTH);

    	/* =========================================
    	 * MENU LATERAL
    	 * ========================================= */
    	JPanel panelMenu = new JPanel();
    	panelMenu.setBackground(new Color(15, 70, 90));
    	panelMenu.setPreferredSize(new Dimension(230, 0));
    	panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
    	panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

    	btnStudents = crearBotonMenu("Estudiantes");
    	btnCourses = crearBotonMenu("Cursos");
    	btnEvaluaciones = crearBotonMenu("Evaluaciones");
    	btnInscripciones = crearBotonMenu("Inscripciones");
    	btnSalir = crearBotonMenu("Salir");

    	panelMenu.add(btnStudents);
    	panelMenu.add(Box.createRigidArea(new Dimension(0, 15)));

    	panelMenu.add(btnCourses);
    	panelMenu.add(Box.createRigidArea(new Dimension(0, 15)));

    	panelMenu.add(btnEvaluaciones);
    	panelMenu.add(Box.createRigidArea(new Dimension(0, 15)));

    	panelMenu.add(btnInscripciones);
    	panelMenu.add(Box.createRigidArea(new Dimension(0, 15)));

    	panelMenu.add(btnSalir);

    	add(panelMenu, BorderLayout.WEST);

    	/* =========================================
    	 * PANEL CENTRAL
    	 * ========================================= */
    	JPanel panelCentral = new JPanel();
    	panelCentral.setBackground(new Color(245, 245, 245));
    	panelCentral.setLayout(new GridLayout(1, 1));

    	JPanel panelBienvenida = new JPanel();
    	panelBienvenida.setBackground(Color.WHITE);
    	panelBienvenida.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
    	panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));

    	JLabel lblBienvenida = new JLabel("Bienvenido a Campus Lite,");
    	lblBienvenida.setFont(new Font("Arial", Font.BOLD, 42));
    	lblBienvenida.setAlignmentX(CENTER_ALIGNMENT);

    	JLabel lblSistema = new JLabel("Tu Sistema Académico");
    	lblSistema.setFont(new Font("Arial", Font.BOLD, 38));
    	lblSistema.setAlignmentX(CENTER_ALIGNMENT);

    	panelBienvenida.add(Box.createVerticalGlue());
    	panelBienvenida.add(lblBienvenida);
    	panelBienvenida.add(Box.createRigidArea(new Dimension(0, 20)));
    	panelBienvenida.add(lblSistema);
    	panelBienvenida.add(Box.createVerticalGlue());

    	panelCentral.add(panelBienvenida);

    	add(panelCentral, BorderLayout.CENTER);
    	
    }
    
    /* =========================================
	 * BOTÓN MENU
	 * ========================================= */
	private JButton crearBotonMenu(String texto) {

	    JButton boton = new JButton(texto);

	    boton.setMaximumSize(new Dimension(190, 60));
	    boton.setPreferredSize(new Dimension(190, 60));

	    boton.setHorizontalAlignment(SwingConstants.CENTER);

	    boton.setFocusPainted(false);

	    boton.setBackground(new Color(40, 140, 220));
	    boton.setForeground(Color.BLACK);

	    boton.setFont(new Font("Arial", Font.BOLD, 18));

	    boton.setBorder(BorderFactory.createEmptyBorder());

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

        btnStudents.addActionListener (e -> {
            
            PantallaEstudiantes ventana = new PantallaEstudiantes();
            ventana.setVisible(true);
            
            dispose();
        });

        btnCourses.addActionListener(e -> {

            EduManagerFrame ventana = new EduManagerFrame();
            ventana.setVisible(true);

            dispose(); // cierra Dashboard si se quiere cambiar de pantalla
        });

        btnEvaluaciones.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Módulo reportes."
            );
        });

        btnInscripciones.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Configuraciones del sistema."
            );
        });
        
        btnSalir.addActionListener(e -> {

            System.exit(0);
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
