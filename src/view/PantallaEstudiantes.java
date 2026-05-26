package view;
import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import model.Estudiante;

public class PantallaEstudiantes extends JFrame {

    // Componentes del formulario
    private JTextField txtCarnet;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JButton btnRegistrar; // Lo sacamos aquí para poder cambiarle el texto a "Actualizar"
    
    // Componentes de la tabla
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;
    
    // Lista y control de estado
    private ArrayList<Estudiante> listaEstudiantes;
    private int filaEnEdicion = -1; // -1 significa que estamos creando uno nuevo. >=0 es la fila que editamos.

    public PantallaEstudiantes() {
        listaEstudiantes = new ArrayList<>();

        setTitle("EduManager Desktop - Gestión de Estudiantes");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        /* =========================================
         * 1. MENÚ PRINCIPAL LATERAL
         * ========================================= */
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(40, 50, 70));
        panelMenu.setPreferredSize(new Dimension(180, 0));
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        
        JButton btnDashboard = new JButton("Dashboard Principal");
        btnDashboard.setMaximumSize(new Dimension(180, 40));
        
        JButton btnStudent = new JButton("Gestión Estudiantes");
        btnStudent.setMaximumSize(new Dimension(180, 40));
        btnStudent.setBackground(Color.WHITE); 
        
        JButton btnCatalog = new JButton("Catalogo de cursos");
        btnCatalog.setMaximumSize(new Dimension(180, 40));
        btnCatalog.setBackground(Color.WHITE); 
        
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20))); 
        panelMenu.add(btnDashboard);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMenu.add(btnStudent);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 20))); 
        panelMenu.add(btnCatalog);
        
        add(panelMenu, BorderLayout.WEST);

        /* =========================================
         * 2. PANEL CENTRAL (Formulario)
         * ========================================= */
        JPanel panelCentral = new JPanel(new BorderLayout());
        
        JPanel panelFormulario = new JPanel(new GridLayout(2, 4, 10, 15));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Gestión de Estudiantes - Registro"));
        
        txtCarnet = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtCorreo = new JTextField();
        
        panelFormulario.add(new JLabel("Carnet:"));
        panelFormulario.add(txtCarnet);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellido);
        panelFormulario.add(new JLabel("Correo Electrónico:"));
        panelFormulario.add(txtCorreo);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnRegistrar = new JButton("Registrar");
        JButton btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelFormulario, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);
        
        panelCentral.add(panelNorte, BorderLayout.NORTH);

        /* =========================================
         * 3. TABLA DE ESTUDIANTES (Personalizada)
         * ========================================= */
        String[] columnas = {"Carnet", "Nombre", "Apellido", "Correo", "Acciones"};
        
        // Sobrescribimos el modelo para hacer que el texto NO sea editable, pero los botones SÍ
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo la columna 4 (Acciones) permite clics
                return column == 4; 
            }
        };
        
        tablaEstudiantes = new JTable(modeloTabla);
        tablaEstudiantes.setRowHeight(35); // Hacemos las filas más altas para que quepan los botones
        
        // Aplicamos el "Dibujante" y el "Editor" a la columna de Acciones
        tablaEstudiantes.getColumnModel().getColumn(4).setCellRenderer(new BotonesRenderer());
        tablaEstudiantes.getColumnModel().getColumn(4).setCellEditor(new BotonesEditor());
        
        JScrollPane scrollTabla = new JScrollPane(tablaEstudiantes);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Listado de Estudiantes Matriculados"));
        
        panelCentral.add(scrollTabla, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        /* =========================================
         * EVENTOS
         * ========================================= */
        btnRegistrar.addActionListener(e -> guardarOActualizarEstudiante());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        //Ejecutamos Regresar a la pantalla Menu Principal
        btnDashboard.addActionListener (e -> {
            
            DashboardView ventana = new DashboardView();
            ventana.setVisible(true);
            
            dispose();
        });
         // Ejecutamos la pantalla en el hilo de Swing
        btnCatalog.addActionListener(e -> java.awt.EventQueue.invokeLater(() -> {
            new EduManagerFrame().setVisible(true);
        }));
        btnCatalog.addActionListener(e -> dispose());
        
    }

    // --- LÓGICA DE REGISTRO Y ACTUALIZACIÓN ---
    private void guardarOActualizarEstudiante() {
        String carnet = txtCarnet.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (carnet.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos obligatorios.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (filaEnEdicion == -1) {
                // MODO: REGISTRAR NUEVO
                Estudiante nuevoEstudiante = new Estudiante(carnet, nombre, apellido, correo);
                listaEstudiantes.add(nuevoEstudiante);
                
                Object[] fila = {nuevoEstudiante.getCarnet(), nuevoEstudiante.getNombre(), nuevoEstudiante.getApellido(), nuevoEstudiante.getCorreo(), ""};
                modeloTabla.addRow(fila);
                JOptionPane.showMessageDialog(this, "Registrado con éxito!");
                
            } else {
                // MODO: ACTUALIZAR EXISTENTE
                Estudiante est = listaEstudiantes.get(filaEnEdicion);
                // El carnet no se cambia, actualizamos lo demás
                est.setNombre(nombre);
                est.setApellido(apellido);
                est.setCorreo(correo);
                
                // Actualizamos la tabla visualmente
                modeloTabla.setValueAt(nombre, filaEnEdicion, 1);
                modeloTabla.setValueAt(apellido, filaEnEdicion, 2);
                modeloTabla.setValueAt(correo, filaEnEdicion, 3);
                
                JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente.");
            }
            limpiarCampos();
            
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCarnet.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtCarnet.setEnabled(true); // Volvemos a habilitar el carnet
        btnRegistrar.setText("Registrar"); // El botón vuelve a la normalidad
        filaEnEdicion = -1; // Reseteamos el estado
        txtCarnet.requestFocus();
    }

    // Métodos que son llamados al hacer clic en los botones de la tabla
    private void prepararEdicion(int fila) {
        filaEnEdicion = fila;
        Estudiante est = listaEstudiantes.get(fila);
        
        // Subimos los datos al formulario
        txtCarnet.setText(est.getCarnet());
        txtNombre.setText(est.getNombre());
        txtApellido.setText(est.getApellido());
        txtCorreo.setText(est.getCorreo());
        
        // Bloqueamos el carnet y cambiamos el texto del botón
        txtCarnet.setEnabled(false);
        btnRegistrar.setText("Actualizar");
    }

    private void eliminarEstudiante(int fila) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar a este estudiante?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(confirm == JOptionPane.YES_OPTION) {
            listaEstudiantes.remove(fila);
            modeloTabla.removeRow(fila);
            // Si estábamos editando a este mismo, limpiamos el formulario
            if (filaEnEdicion == fila) {
                limpiarCampos();
            }
        }
    }

    /* =========================================================
     * CLASES INTERNAS PARA DIBUJAR Y CONTROLAR LOS BOTONES
     * ========================================================= */
    
    // 1. Dibuja los botones en la tabla
    class BotonesRenderer extends JPanel implements TableCellRenderer {
        public BotonesRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
            JButton btnEditar = new JButton("✎");
            btnEditar.setForeground(Color.BLUE);
            JButton btnEliminar = new JButton("🗑️");
            btnEliminar.setForeground(Color.RED);
            
            add(btnEditar);
            add(btnEliminar);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }

    // 2. Detecta los clics en los botones
    class BotonesEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel;
        private int filaActual;

        public BotonesEditor() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
            JButton btnEditar = new JButton("✎");
            btnEditar.setForeground(Color.BLUE);
            JButton btnEliminar = new JButton("🗑️");
            btnEliminar.setForeground(Color.RED);

            btnEditar.addActionListener(e -> {
                fireEditingStopped(); // Fundamental: le avisa a Swing que ya dejamos de hacer clic
                prepararEdicion(filaActual);
            });

            btnEliminar.addActionListener(e -> {
                fireEditingStopped();
                eliminarEstudiante(filaActual);
            });

            panel.add(btnEditar);
            panel.add(btnEliminar);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.filaActual = row;
            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }

}
