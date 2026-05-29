package view;

import model.Curso;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class CoursesView extends JFrame {

	// Componentes del Formulario Curso
	private JTextField txtNombreCurso, txtCodigoCurso, txtCupoCurso;

	private JButton btnGuardarCurso;
	private JButton btnLimpiarCurso;
	private JButton btnEditarCurso;
	private JButton btnEliminarCurso;
	private JButton btnVolver;
    
    // Tablas de la Interfaz
    private JTable tablaCursos;
    private DefaultTableModel modeloTablaCursos;
    
    // Listas de datos en memoria (Simulación de Base de Datos)
    private List<Curso> listaCursos;
    private int contadorIdCurso = 1;

    public CoursesView() {
        // 1. CONFIGURACIÓN DE LA VENTANA PRINCIPAL
        setTitle("EDUMANAGER DESKTOP - v2.4");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicializar listas de datos
        listaCursos = new ArrayList<>();
        

     // PANEL PRINCIPAL
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));


        // =====================================================
        // TÍTULO
        // =====================================================

        JLabel lblTitulo = new JLabel("Gestión de Cursos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(10, 60, 80));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 10, 25, 0));

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);


        // =====================================================
        // CONTENIDO CENTRAL
        // =====================================================

        JPanel panelContenido = new JPanel(new BorderLayout(25, 0));
        panelContenido.setBackground(new Color(245, 247, 250));


        // =====================================================
        // PANEL IZQUIERDO (FORMULARIO)
        // =====================================================

        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(new Color(15, 70, 90));
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(10, 60, 80), 2),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JPanel panelTituloFormulario = new JPanel();
        panelTituloFormulario.setLayout(new BoxLayout(panelTituloFormulario, BoxLayout.Y_AXIS));
        panelTituloFormulario.setBackground(new Color(15, 70, 90));

        JLabel lblRegistrar = new JLabel("Registrar");
        lblRegistrar.setFont(new Font("Arial", Font.BOLD, 28));
        lblRegistrar.setForeground(Color.WHITE);
        lblRegistrar.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblNuevoCurso = new JLabel("Nuevo Curso");
        lblNuevoCurso.setFont(new Font("Arial", Font.BOLD, 28));
        lblNuevoCurso.setForeground(Color.WHITE);
        lblNuevoCurso.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelTituloFormulario.add(lblRegistrar);
        panelTituloFormulario.add(lblNuevoCurso);

        gbc.gridx = 0;
        gbc.gridy = 0;

        panelFormulario.add(panelTituloFormulario, gbc);


        // =====================
        // CÓDIGO
        // =====================

        gbc.gridy++;

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 16));
        lblCodigo.setForeground(Color.WHITE);
        panelFormulario.add(lblCodigo, gbc);

        gbc.gridy++;

        txtCodigoCurso = new JTextField();
        txtCodigoCurso.setPreferredSize(new Dimension(180, 35));
        txtCodigoCurso.setFont(new Font("Arial", Font.PLAIN, 16));
        panelFormulario.add(txtCodigoCurso, gbc);


        // =====================
        // NOMBRE
        // =====================

        gbc.gridy++;

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setForeground(Color.WHITE);
        panelFormulario.add(lblNombre, gbc);

        gbc.gridy++;

        txtNombreCurso = new JTextField();
        txtNombreCurso.setPreferredSize(new Dimension(180, 35));
        txtNombreCurso.setFont(new Font("Arial", Font.PLAIN, 16));
        panelFormulario.add(txtNombreCurso, gbc);


        // =====================
        // CUPO
        // =====================

        gbc.gridy++;

        JLabel lblCupo = new JLabel("Cupo");
        lblCupo.setFont(new Font("Arial", Font.BOLD, 16));
        lblCupo.setForeground(Color.WHITE);
        panelFormulario.add(lblCupo, gbc);

        gbc.gridy++;

        txtCupoCurso = new JTextField();
        txtCupoCurso.setPreferredSize(new Dimension(180, 35));
        txtCupoCurso.setFont(new Font("Arial", Font.PLAIN, 16));
        panelFormulario.add(txtCupoCurso, gbc);


        // =====================================================
        // BOTONES
        // =====================================================

        gbc.gridy++;

        btnGuardarCurso = new JButton("Registrar Curso");
        btnLimpiarCurso = new JButton("Limpiar");
        btnVolver = new JButton("Volver al Menú");


        // ESTILO BOTONES
        JButton[] botones = {
                btnGuardarCurso,
                btnLimpiarCurso,
                btnVolver
        };

        for (JButton boton : botones) {

            boton.setFocusPainted(false);
            boton.setFont(new Font("Arial", Font.BOLD, 15));
            boton.setPreferredSize(new Dimension(200, 42));
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            boton.setForeground(Color.BLACK);

            if (boton == btnGuardarCurso) {
            	
                boton.setBackground(new Color(144, 238, 144));

            } else if (boton == btnLimpiarCurso) {
                boton.setBackground(new Color(173, 216, 230));

            } else if (boton == btnVolver) {
                boton.setBackground(new Color(211, 211, 211));
            }
        }
            
            JPanel panelSoloBotones = new JPanel(new GridLayout(3, 1, 0, 10));
            panelSoloBotones.setBackground(new Color(15, 70, 90)); 

            panelSoloBotones.add(btnGuardarCurso);
            panelSoloBotones.add(btnLimpiarCurso);
            panelSoloBotones.add(btnVolver);

            panelFormulario.add(panelSoloBotones, gbc);
        


        // =====================================================
        // PANEL DERECHO (TABLA)
        // =====================================================

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);

        panelTabla.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTabla = new JLabel("Cursos Registrados");
        lblTabla.setFont(new Font("Arial", Font.BOLD, 24));
        lblTabla.setForeground(new Color(10, 60, 80));

        panelTabla.add(lblTabla, BorderLayout.NORTH);


        // =====================================================
        // TABLA
        // =====================================================

        String[] columnasCursos = {
                "No.",
                "Código",
                "Nombre",
                "Cupo",
                "Acciones"
        };

        modeloTablaCursos = new DefaultTableModel(columnasCursos, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaCursos = new JTable(modeloTablaCursos);
        tablaCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {

                int fila = tablaCursos.rowAtPoint(e.getPoint());
                int columna = tablaCursos.columnAtPoint(e.getPoint());

                if (columna == 4) { // columna Acciones

                    int confirmacion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Desea eliminar este curso?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {

                        listaCursos.remove(fila);
                        actualizarTablaCursos();

                        JOptionPane.showMessageDialog(null, "Curso eliminado correctamente.");
                    }
                }
            }
        });
        tablaCursos.getTableHeader().setReorderingAllowed(false);
        tablaCursos.getColumnModel().getColumn(4).setPreferredWidth(100);

        tablaCursos.setRowHeight(32);

        tablaCursos.setFont(new Font("Arial", Font.PLAIN, 14));

        tablaCursos.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        tablaCursos.getTableHeader().setBackground(
                new Color(10, 60, 80)
        );

        tablaCursos.getTableHeader().setForeground(Color.BLACK);

        JScrollPane scrollCursos = new JScrollPane(tablaCursos);

        scrollCursos.setBorder(
                BorderFactory.createEmptyBorder(15, 0, 0, 0)
        );

        panelTabla.add(scrollCursos, BorderLayout.CENTER);


        // =====================================================
        // AGREGAR PANELES
        // =====================================================

        panelFormulario.setPreferredSize(new Dimension(320, 0));

        panelContenido.add(panelFormulario, BorderLayout.WEST);
        panelContenido.add(panelTabla, BorderLayout.CENTER);

        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);

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
        
        btnVolver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                DashboardView dashboard = new DashboardView();

                dashboard.setVisible(true);

                dispose();
            }
        });

        // Carga inicial en las tablas gráficas
        actualizarTablaCursos();
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
        
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "El nombre solo puede contener letras.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }
        
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
        	        c.getCupo(),
        	        "<html><div style='text-align:center;'>" +
        	        "<span style='background-color:#e74c3c; color:white; padding:5px 12px; " +
        	        "border-radius:6px; font-weight:bold;'>✖</span></div></html>"
        	};

            modeloTablaCursos.addRow(fila);
        }
    }

   
}

