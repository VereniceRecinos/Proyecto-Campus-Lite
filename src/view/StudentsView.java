package view;

import model.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentsView extends JFrame {

    // Componentes del Formulario
    private JTextField txtStudentId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtEmail;
    
    private JButton btnGenerateEmail;
    private JButton btnSave;
    private JButton btnClear;
    private JButton btnBack;
    private JButton btnExit;
    private JButton btnEdit;
    private JButton btnDelete;
    
    private JTable studentTable;
    private DefaultTableModel tableModel;
    
    // Lista de datos y estado
    private List<Estudiante> studentList;
    private boolean isEditing = false;
    private int editingIndex = -1;

    public StudentsView() {
        // 1. Configuracion de la ventana
        setTitle("EDUMANAGER DESKTOP - v2.4");
        setSize(1050, 650); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        studentList = new ArrayList<>();

        // Panel Principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Titulo
        JLabel lblTitle = new JLabel("Gestión de Estudiantes");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitle.setForeground(new Color(10, 60, 80));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 25, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Contenido centrado
        JPanel contentPanel = new JPanel(new BorderLayout(25, 0));
        contentPanel.setBackground(new Color(245, 247, 250));

        // Panel izquierdo (formulario)
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

        JLabel lblRegister = new JLabel("Registrar");
        lblRegister.setFont(new Font("Arial", Font.BOLD, 28));
        lblRegister.setForeground(Color.WHITE);
        
        JLabel lblNewStudent = new JLabel("Estudiante");
        lblNewStudent.setFont(new Font("Arial", Font.BOLD, 28));
        lblNewStudent.setForeground(Color.WHITE);

        formTitlePanel.add(lblRegister);
        formTitlePanel.add(lblNewStudent);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(formTitlePanel, gbc);

        // --- CARNET ---
        gbc.gridy++;
        formPanel.add(createWhiteLabel("Carnet (Números/Guiones)"), gbc);
        gbc.gridy++;
        txtStudentId = createTextField();
        validateStudentId(txtStudentId);
        formPanel.add(txtStudentId, gbc);

        // --- NOMBRES ---
        gbc.gridy++;
        formPanel.add(createWhiteLabel("Nombres (Letras)"), gbc);
        gbc.gridy++;
        txtFirstName = createTextField();
        validateLetters(txtFirstName);
        formPanel.add(txtFirstName, gbc);

        // --- APELLIDOS ---
        gbc.gridy++;
        formPanel.add(createWhiteLabel("Apellidos (Letras)"), gbc);
        gbc.gridy++;
        txtLastName = createTextField();
        validateLetters(txtLastName);
        formPanel.add(txtLastName, gbc);

        // --- CORREO ---
        gbc.gridy++;
        formPanel.add(createWhiteLabel("Correo Autogenerado"), gbc);
        gbc.gridy++;
        txtEmail = createTextField();
        txtEmail.setEditable(false);
        formPanel.add(txtEmail, gbc);

        // Botones de formulario
        gbc.gridy++;
        
        btnGenerateEmail = new JButton("Generar Correo");
        btnSave = new JButton("Registrar Estudiante");
        btnClear = new JButton("Limpiar");
        btnBack = new JButton("Volver al Menú");
        btnExit = new JButton("Salir");

        styleButton(btnGenerateEmail, new Color(255, 255, 204)); 
        styleButton(btnSave, new Color(144, 238, 144));         
        styleButton(btnClear, new Color(173, 216, 230));        
        styleButton(btnBack, new Color(211, 211, 211));         
        styleButton(btnExit, new Color(255, 182, 193));         

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 0, 8));
        buttonPanel.setBackground(new Color(15, 70, 90)); 
        buttonPanel.add(btnGenerateEmail);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnBack);
        buttonPanel.add(btnExit);

        formPanel.add(buttonPanel, gbc);

        // Panel Derecho (Tabla)
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTableTitle = new JLabel("Estudiantes Registrados");
        lblTableTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTableTitle.setForeground(new Color(10, 60, 80));
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        tablePanel.add(lblTableTitle, BorderLayout.NORTH);

        // Configuracion de la tabla
        String[] columns = {"No.", "Carnet", "Nombres", "Apellidos", "Correo"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(32);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        studentTable.setBackground(Color.WHITE);
        studentTable.setForeground(Color.BLACK);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        studentTable.getTableHeader().setBackground(new Color(230, 235, 240));
        studentTable.getTableHeader().setForeground(Color.BLACK);
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Panel de acciones de la tabla
        JPanel tableActionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tableActionsPanel.setBackground(Color.WHITE);
        
        btnEdit = new JButton("Editar Seleccionado");
        btnDelete = new JButton("Eliminar Seleccionado");
        
        styleButton(btnEdit, new Color(255, 228, 181)); // Naranja claro
        styleButton(btnDelete, new Color(255, 182, 193)); // Rojo claro

        tableActionsPanel.add(btnEdit);
        tableActionsPanel.add(btnDelete);
        
        tablePanel.add(tableActionsPanel, BorderLayout.SOUTH);

        // Agregar Paneles
        formPanel.setPreferredSize(new Dimension(320, 0));
        contentPanel.add(formPanel, BorderLayout.WEST);
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // Eventos
        btnGenerateEmail.addActionListener(e -> generateEmail());
        btnSave.addActionListener(e -> saveStudent());
        btnClear.addActionListener(e -> clearForm());
        btnEdit.addActionListener(e -> loadStudentForEditing());
        btnDelete.addActionListener(e -> deleteSelectedStudent());
        
        btnBack.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });
        
        btnExit.addActionListener(e -> System.exit(0));

        updateStudentTable();
    }

    // --- UI ayudantes ---
    private JLabel createWhiteLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(180, 32));
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        return field;
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(200, 38));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
    }

    // --- Validaciones en tiempo real ---
    private void validateStudentId(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '-') e.consume();
            }
        });
    }

    private void validateLetters(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE) e.consume();
            }
        });
    }

    // --- Logica de negocios ---
    private void generateEmail() {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar nombres y apellidos para generar el correo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] firstNamesArray = firstName.split(" ");
        String initial = firstNamesArray[0].substring(0, 1).toLowerCase();

        String[] lastNamesArray = lastName.split(" ");
        String firstLastName = lastNamesArray[0].toLowerCase();

        String baseEmail = initial + firstLastName;
        String proposedEmail = baseEmail + "@gmail.com";

        if (isEditing && studentList.get(editingIndex).getCorreo().equals(proposedEmail)) {
             txtEmail.setText(proposedEmail);
             return;
        }

        if (emailExists(proposedEmail)) {
            Random rand = new Random();
            int randomNum = rand.nextInt(900) + 100; 
            proposedEmail = baseEmail + randomNum + "@gmail.com";
        }

        txtEmail.setText(proposedEmail);
    }

    private boolean emailExists(String email) {
        for (int i = 0; i < studentList.size(); i++) {
            if (isEditing && i == editingIndex) continue;
            if (studentList.get(i).getCorreo().equals(email)) return true;
        }
        return false;
    }

    private void saveStudent() {
        String studentId = txtStudentId.getText().trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();

        if (studentId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios (Genere el correo).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isEditing) {
            Estudiante s = studentList.get(editingIndex);
            s.setNombre(firstName);
            s.setApellido(lastName);
            s.setCorreo(email);
            JOptionPane.showMessageDialog(this, "Estudiante actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Estudiante s : studentList) {
                if (s.getCarnet().equals(studentId)) {
                    JOptionPane.showMessageDialog(this, "El carnet ya existe.", "Duplicado", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            try {
                Estudiante newStudent = new Estudiante(studentId, firstName, lastName, email);
                studentList.add(newStudent);
                JOptionPane.showMessageDialog(this, "Estudiante guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        updateStudentTable();
        clearForm();
    }

    // --- LÓGICA DE EDICIÓN ---
    private void loadStudentForEditing() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante haciendo clic en la tabla primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Se extraen los datos de la memoria y se suben al formulario
        Estudiante s = studentList.get(selectedRow);
        txtStudentId.setText(s.getCarnet());
        txtStudentId.setEditable(false); // Se bloquea para no alterar la llave primaria
        txtFirstName.setText(s.getNombre());
        txtLastName.setText(s.getApellido());
        txtEmail.setText(s.getCorreo());

        isEditing = true;
        editingIndex = selectedRow;
        btnSave.setText("Actualizar Estudiante"); // El botón de guardar cambia de función
    }

    private void deleteSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante de la tabla para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            studentList.remove(selectedRow);
            updateStudentTable();
            if(isEditing && editingIndex == selectedRow) clearForm();
        }
    }

    private void updateStudentTable() {
        tableModel.setRowCount(0);
        int index = 1;
        for (Estudiante s : studentList) {
            Object[] row = {
                String.format("%03d", index++),
                s.getCarnet(),
                s.getNombre(),
                s.getApellido(),
                s.getCorreo()
            };
            tableModel.addRow(row);
        }
        
        tableModel.fireTableDataChanged();
        studentTable.revalidate();
        studentTable.repaint();
    }

    private void clearForm() {
        txtStudentId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtStudentId.setEditable(true);
        isEditing = false;
        editingIndex = -1;
        btnSave.setText("Registrar Estudiante"); // Regresa a su estado original
    }
}
