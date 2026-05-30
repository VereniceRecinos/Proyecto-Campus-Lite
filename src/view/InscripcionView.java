package view;

import model.Student;
import model.Course;
import model.Enrollment;
import model.Evaluation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InscripcionView extends JFrame {

    // Componentes Unificados
    private JComboBox<String> cmbStudents;
    private JComboBox<String> cmbCourses;
    private JTextField txtDate;
    
    private JButton btnEnroll;
    private JButton btnClear;
    private JButton btnBack;
    private JButton btnExit;
    private JButton btnDelete;

    private JTable enrollmentTable;
    private DefaultTableModel tableModel;

    // Listas de Datos Reales
    private List<Student> studentList; 
    private List<Course> courseList;       
    private List<Enrollment> enrollmentList;
    private List<Evaluation> evaluationList;
    
    // Constructor con parámetros
    
    public InscripcionView(List<Student> studentList, List<Course> courseList, List<Enrollment> enrollmentList, List<Evaluation> evaluationList) {
        this.studentList = studentList;
        this.courseList = courseList;
        this.enrollmentList = enrollmentList;
        this.evaluationList = evaluationList;
        
        // 1. CONFIGURACIÓN VENTANA
        setTitle("EDUMANAGER DESKTOP - v2.4");
        setSize(1050, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // PANEL PRINCIPAL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // TÍTULO
        JLabel lblTitle = new JLabel("Gestión de Inscripciones");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitle.setForeground(new Color(10, 60, 80));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 25, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // CONTENIDO CENTRAL
        JPanel contentPanel = new JPanel(new BorderLayout(25, 0));
        contentPanel.setBackground(new Color(245, 247, 250));

        // =====================================================
        // PANEL IZQUIERDO (FORMULARIO)
        // =====================================================
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(15, 70, 90));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(10, 60, 80), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JPanel formTitlePanel = new JPanel();
        formTitlePanel.setLayout(new BoxLayout(formTitlePanel, BoxLayout.Y_AXIS));
        formTitlePanel.setBackground(new Color(15, 70, 90));

        JLabel lblRegister = new JLabel("Inscribir");
        lblRegister.setFont(new Font("Arial", Font.BOLD, 28));
        lblRegister.setForeground(Color.WHITE);
        
        JLabel lblNewEnroll = new JLabel("Nuevo Alumno");
        lblNewEnroll.setFont(new Font("Arial", Font.BOLD, 28));
        lblNewEnroll.setForeground(Color.WHITE);

        formTitlePanel.add(lblRegister);
        formTitlePanel.add(lblNewEnroll);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(formTitlePanel, gbc);

        // --- SELECCIONAR ESTUDIANTE ---
        gbc.gridy++;
        formPanel.add(createWhiteLabel("Seleccionar Estudiante:"), gbc);
        gbc.gridy++;
        cmbStudents = new JComboBox<>();
        formPanel.add(cmbStudents, gbc);

        // --- SELECCIONAR CURSO ---
        gbc.gridy++;
        formPanel.add(createWhiteLabel("Seleccionar Curso:"), gbc);
        gbc.gridy++;
        cmbCourses = new JComboBox<>();
        formPanel.add(cmbCourses, gbc);

        // --- FECHA ---
        gbc.gridy++;
        formPanel.add(createWhiteLabel("Fecha de Inscripción:"), gbc);
        gbc.gridy++;
        txtDate = new JTextField();
        txtDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtDate.setPreferredSize(new Dimension(180, 32));
        formPanel.add(txtDate, gbc);

        // BOTONES
        gbc.gridy++;
        btnEnroll = createStyledButton("Realizar Inscripción", new Color(144, 238, 144));
        btnClear = createStyledButton("Limpiar", new Color(173, 216, 230));
        btnBack = createStyledButton("Volver al Menú", new Color(211, 211, 211));
        btnExit = createStyledButton("Salir", new Color(255, 182, 193));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 8));
        buttonPanel.setBackground(new Color(15, 70, 90));
        buttonPanel.add(btnEnroll);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnBack);
        buttonPanel.add(btnExit);

        formPanel.add(buttonPanel, gbc);

        // =====================================================
        // PANEL DERECHO (TABLA)
        // =====================================================
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTableTitle = new JLabel("Historial de Inscripciones");
        lblTableTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTableTitle.setForeground(new Color(10, 60, 80));
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        tablePanel.add(lblTableTitle, BorderLayout.NORTH);

        String[] columns = {"No.", "Estudiante (Carnet)", "Curso (Código)", "Fecha"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        enrollmentTable = new JTable(tableModel);
        enrollmentTable.setRowHeight(32);
        enrollmentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        enrollmentTable.getTableHeader().setBackground(new Color(230, 235, 240));
        
        enrollmentTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(enrollmentTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // BOTÓN ELIMINAR
        JPanel tableActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tableActions.setBackground(Color.WHITE);
        btnDelete = createStyledButton("Anular Inscripción", new Color(255, 182, 193));
        btnDelete.setPreferredSize(new Dimension(180, 35));
        tableActions.add(btnDelete);
        tablePanel.add(tableActions, BorderLayout.SOUTH);

        // ENSAMBLAR PANELES
        formPanel.setPreferredSize(new Dimension(320, 0));
        contentPanel.add(formPanel, BorderLayout.WEST);
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // EVENTOS
        btnEnroll.addActionListener(e -> enrollStudent());
        btnClear.addActionListener(e -> clearForm());
        btnExit.addActionListener(e -> System.exit(0));
        btnDelete.addActionListener(e -> deleteEnrollment());
        
        btnBack.addActionListener(e -> { 
            // Devuelve las listas al menú para no perder la info
            new DashboardView(this.studentList, this.courseList, this.enrollmentList, null).setVisible(true); 
            dispose(); 
        });
        
        // Cargar los datos y la tabla al abrir la ventana
        loadComboData();
        updateTable();
    }
    
    private void loadComboData() {
        cmbStudents.removeAllItems();
        cmbCourses.removeAllItems();

        cmbStudents.addItem("-- Seleccione un Estudiante --");
        cmbCourses.addItem("-- Seleccione un Curso --");

        for (Student student : studentList) {
            cmbStudents.addItem(student.getFirstName() + " " + student.getLastName() + " (" + student.getStudentId() + ")");
        }

        for (Course course : courseList) {
            cmbCourses.addItem(course.getName() + " (" + course.getCode() + ")");
        }
    }

    // --- LÓGICA ---

    private void enrollStudent() {
        int studentIdx = cmbStudents.getSelectedIndex();
        int courseIdx = cmbCourses.getSelectedIndex();
        String date = txtDate.getText().trim();

        if (studentIdx == 0 || courseIdx == 0 || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante y un curso válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student s = studentList.get(studentIdx - 1);
        Course c = courseList.get(courseIdx - 1);

        for (Enrollment ins : enrollmentList) {
            if (ins.getStudent().getStudentId().equals(s.getStudentId()) && ins.getCourse().getCode().equals(c.getCode())) {
                JOptionPane.showMessageDialog(this, "Este estudiante ya está inscrito en este curso.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        Enrollment nueva = new Enrollment(s, c, date);
        enrollmentList.add(nueva);
        
        utils.PersistenceManager.saveData(studentList, courseList, enrollmentList, evaluationList);
        
        updateTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "¡Inscripción exitosa!");
    }

    private void deleteEnrollment() {
        int row = enrollmentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una inscripción para anular.");
            return;
        }
        enrollmentList.remove(row);
        
        utils.PersistenceManager.saveData(studentList, courseList, enrollmentList, evaluationList);
        
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        int i = 1;
        for (Enrollment ins : enrollmentList) {
            Object[] row = {
                String.format("%03d", i++),
                ins.getStudent().getFirstName() + " (" + ins.getStudent().getStudentId() + ")",
                ins.getCourse().getName() + " (" + ins.getCourse().getCode() + ")",
                ins.getEnrollmentDate()
            };
            tableModel.addRow(row);
        }
    }

    private void clearForm() {
        cmbStudents.setSelectedIndex(0);
        cmbCourses.setSelectedIndex(0);
        txtDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    // --- HELPERS UI ---
    private JLabel createWhiteLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        return btn;
    }
}