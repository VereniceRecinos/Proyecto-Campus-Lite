package view;

import model.Curso;
import model.Evaluacion;
import model.ExamenEscrito;
import model.Laboratorio;
import model.Proyecto;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EduManagerFrame extends JFrame {

    // Componentes del Formulario Curso
    private JTextField txtNombreCurso, txtCodigoCurso, txtCupoCurso;
    private JButton btnGuardarCurso, btnLimpiarCurso;
    
    // Tablas de la Interfaz
    private JTable tablaCursos;
    private DefaultTableModel modeloTablaCursos;
    private JTable tablaEvaluaciones;
    private DefaultTableModel modeloTablaEvaluaciones;
    
    // Listas de datos en memoria (Simulación de Base de Datos)
    private List<Curso> listaCursos;
    private List<Evaluacion> listaEvaluaciones;
    private int contadorIdCurso = 1;

    public EduManagerFrame() {
        // 1. CONFIGURACIÓN DE LA VENTANA PRINCIPAL
        setTitle("EDUMANAGER DESKTOP - v2.4");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicializar listas de datos
        listaCursos = new ArrayList<>();
        listaEvaluaciones = new ArrayList<>();
        
        
        inicializarDatosEjemplo();

        // 2. DISEÑO DEL ENCABEZADO SUPERIOR
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(45, 118, 185));
        panelHeader.setPreferredSize(new Dimension(1000, 45));
        
        JLabel lblTituloApp = new JLabel("  EDUMANAGER DESKTOP", SwingConstants.LEFT);
        lblTituloApp.setForeground(Color.WHITE);
        lblTituloApp.setFont(new Font("Arial", Font.BOLD, 13));
        
        JLabel lblUsuario = new JLabel("Admin User  [AU]  ", SwingConstants.RIGHT);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
        
        panelHeader.add(lblTituloApp, BorderLayout.WEST);
        panelHeader.add(lblUsuario, BorderLayout.EAST);
        add(panelHeader, BorderLayout.NORTH);

        // 3. CONTENEDOR CENTRAL
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título de la sección
        JLabel lblSeccion = new JLabel("Gestión de Cursos y Evaluaciones");
        lblSeccion.setFont(new Font("Arial", Font.BOLD, 18));
        lblSeccion.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panelCentral.add(lblSeccion, BorderLayout.NORTH);

        // Pestañas (JTabbedPane)
        JTabbedPane pestañas = new JTabbedPane();

        // ---- PESTAÑA 1: CURSOS ----
        JPanel panelCursosTab = new JPanel(new BorderLayout(0, 15));
        panelCursosTab.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

        // Formulario: Registrar Nuevo Curso
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        TitledBorder bordeFormulario = BorderFactory.createTitledBorder("REGISTRAR NUEVO CURSO");
        bordeFormulario.setTitleFont(new Font("Arial", Font.BOLD, 11));
        panelFormulario.setBorder(bordeFormulario);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Etiquetas del Formulario
        gbc.gridy = 0;
        gbc.gridx = 0; gbc.weightx = 0.5;
        panelFormulario.add(new JLabel("NOMBRE DEL CURSO"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 0.2;
        panelFormulario.add(new JLabel("CÓDIGO"), gbc);
        
        gbc.gridx = 2; gbc.weightx = 0.3;
        panelFormulario.add(new JLabel("CUPO MÁXIMO"), gbc);

        // Fila 1: Campos de texto
        gbc.gridy = 1;
        gbc.gridx = 0;
        txtNombreCurso = new JTextField();
        panelFormulario.add(txtNombreCurso, gbc);
        
        gbc.gridx = 1;
        txtCodigoCurso = new JTextField();
        panelFormulario.add(txtCodigoCurso, gbc);
        
        gbc.gridx = 2;
        txtCupoCurso = new JTextField();
        panelFormulario.add(txtCupoCurso, gbc);

        // Fila 2: Botones
        gbc.gridy = 2; gbc.gridx = 0; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnGuardarCurso = new JButton("Guardar");
        btnLimpiarCurso = new JButton("Limpiar");
        panelBotones.add(btnGuardarCurso);
        panelBotones.add(btnLimpiarCurso);
        panelFormulario.add(panelBotones, gbc);

        panelCursosTab.add(panelFormulario, BorderLayout.NORTH);

        // Tabla: Mostrar Cursos Registrados
        JPanel panelTablaCursosContenedor = new JPanel(new BorderLayout(0, 5));
        JLabel lblTablaTitulo = new JLabel("Cursos Registrados");
        lblTablaTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        panelTablaCursosContenedor.add(lblTablaTitulo, BorderLayout.NORTH);

        String[] columnasCursos = {"No.", "Código", "Nombre del Curso", "Cupo"};
        modeloTablaCursos = new DefaultTableModel(columnasCursos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaCursos = new JTable(modeloTablaCursos);
        tablaCursos.setRowHeight(22);
        JScrollPane scrollCursos = new JScrollPane(tablaCursos);
        panelTablaCursosContenedor.add(scrollCursos, BorderLayout.CENTER);
        
        panelCursosTab.add(panelTablaCursosContenedor, BorderLayout.CENTER);
        pestañas.addTab("Cursos", panelCursosTab);

        // ---- PESTAÑA 2: EVALUACIONES (Uso de Polimorfismo) ----
        JPanel panelEvaluacionesTab = new JPanel(new BorderLayout(0, 10));
        panelEvaluacionesTab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblTablaEvalTitulo = new JLabel("Historial de Evaluaciones y Cálculos Polimórficos");
        lblTablaEvalTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        panelEvaluacionesTab.add(lblTablaEvalTitulo, BorderLayout.NORTH);

        String[] columnasEval = {"Tipo de Evaluación", "Nombre", "Ponderación (%)", "Puntaje Obtenido Final"};
        modeloTablaEvaluaciones = new DefaultTableModel(columnasEval, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaEvaluaciones = new JTable(modeloTablaEvaluaciones);
        tablaEvaluaciones.setRowHeight(22);
        JScrollPane scrollEval = new JScrollPane(tablaEvaluaciones);
        panelEvaluacionesTab.add(scrollEval, BorderLayout.CENTER);
        
        pestañas.addTab("Evaluaciones", panelEvaluacionesTab);

        panelCentral.add(pestañas, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // 4. CONTROLADORES Y ASIGNACIÓN DE EVENTOS
        btnGuardarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCurso();
            }
        });

        btnLimpiarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormularioCurso();
            }
        });

        // Carga inicial en las tablas gráficas
        actualizarTablaCursos();
        actualizarTablaEvaluaciones();
    }

    // INTERSECCIÓN: VALIDACIÓN Y ASIGNACIÓN CON LA CLASE CURSO
    private void registrarCurso() {
        String nombre = txtNombreCurso.getText().trim();
        String codigo = txtCodigoCurso.getText().trim();
        String cupoStr = txtCupoCurso.getText().trim();

        // Validación de campos vacíos
        if (nombre.isEmpty() || codigo.isEmpty() || cupoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Error: Todos los campos del formulario son obligatorios.", 
                    "Campos Vacíos", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación de formato numérico para el Cupo
        int cupo;
        try {
            cupo = Integer.parseInt(cupoStr);
            if (cupo <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                    "Error: El cupo debe ser un número entero mayor a 0.", 
                    "Error de Formato", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Integración con el objeto 'Curso'
        Curso nuevoCurso = new Curso(codigo, nombre, cupo);
        listaCursos.add(nuevoCurso);

        // Actualizar UI
        actualizarTablaCursos();
        limpiarFormularioCurso();
        JOptionPane.showMessageDialog(this, "¡Curso registrado con éxito!");
    }

    private void limpiarFormularioCurso() {
        txtNombreCurso.setText("");
        txtCodigoCurso.setText("");
        txtCupoCurso.setText("");
    }

    private void actualizarTablaCursos() {
        modeloTablaCursos.setRowCount(0);
        int index = 1;
        for (Curso c : listaCursos) {
            Object[] fila = {
                String.format("%03d", index++),
                c.getCodigo(),
                c.getNombre(),
                c.getCupo()
            };
            modeloTablaCursos.addRow(fila);
        }
    }

    // Segunda pestaña del polimorfismo programado
    private void actualizarTablaEvaluaciones() {
        modeloTablaEvaluaciones.setRowCount(0);
        for (Evaluacion e : listaEvaluaciones) {
            Object[] fila = {
                e.getClass().getSimpleName(), // Obtiene el nombre de la subclase (Proyecto, Laboratorio, etc.)
                e.getNombre(),
                e.getPonderacion() + "%",
                String.format("%.2f", e.puntajeObtenido()) // Llama al método abstracto polimórfico
            };
            modeloTablaEvaluaciones.addRow(fila);
        }
    }

    private void inicializarDatosEjemplo() {
        
        listaCursos.add(new Curso("PROG-01", "Algoritmos Avanzados", 30));
        listaCursos.add(new Curso("BD-202", "Bases de Datos II", 25));
        listaCursos.add(new Curso("SOP-300", "Sistemas Operativos", 40));

        
        listaEvaluaciones.add(new ExamenEscrito("Parcial 1", 30, 95));
        listaEvaluaciones.add(new Laboratorio("Laboratorio 1", 20, 90));
        listaEvaluaciones.add(new Proyecto("Proyecto Final", 50, 88));
    }
}
