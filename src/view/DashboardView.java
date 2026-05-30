package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DashboardView extends JFrame {
	
    // MENU
	private JButton btnStudents;
	private JButton btnCourses;
	private JButton btnEvaluaciones;
	private JButton btnInscripciones;
	private JButton btnSalir;

    // LISTAS MAESTRAS EN INGLÉS
    private List<model.Estudiante> studentList;
    private List<model.Curso> courseList;
    private List<model.Inscripcion> enrollmentList;
    private List<model.Evaluacion> listaEvaluaciones;

    // 1. CONSTRUCTOR PARA EL ARRANQUE DESDE EL MAIN
    public DashboardView() {
        // ¡ESTO ERA LO QUE FALTABA! Inicializar las listas vacías
        this.studentList = new ArrayList<>();
        this.courseList = new ArrayList<>();
        this.enrollmentList = new ArrayList<>();
        
        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    // 2. CONSTRUCTOR PARA CUANDO REGRESAS DE OTRA PANTALLA
    public DashboardView(List<model.Estudiante> s, List<model.Curso> c, List<model.Inscripcion> i, List<model.Evaluacion> e) {
    	this.studentList = s;
        this.courseList = c;
        this.enrollmentList = i;
        this.listaEvaluaciones = (e != null) ? e : new ArrayList<>();
        
        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    private void configurarVentana() {
        setTitle("EduManager Desktop");
        setSize(1365, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
    	// HEADER SUPERIOR
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

    	// MENU LATERAL
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

    	// PANEL CENTRAL
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

    private void agregarEventos() {
        btnStudents.addActionListener(e -> {
            StudentsView window = new StudentsView(studentList, courseList, enrollmentList);
            window.setVisible(true);
            dispose();
        });

        btnCourses.addActionListener(e -> {
            CoursesView window = new CoursesView(studentList, courseList, enrollmentList);
            window.setVisible(true);
            dispose();
        });

        btnInscripciones.addActionListener(e -> {
            InscripcionView window = new InscripcionView(studentList, courseList, enrollmentList);
            window.setVisible(true);
            dispose();
        });

        btnEvaluaciones.addActionListener(e -> {
        	EvaluacionesView window = new EvaluacionesView(studentList, courseList, enrollmentList, listaEvaluaciones);
            window.setVisible(true);
            dispose();
        });
        
        btnSalir.addActionListener(e -> System.exit(0));
    }
}