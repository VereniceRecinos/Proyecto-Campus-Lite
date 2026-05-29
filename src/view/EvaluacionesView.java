package view;

import model.Evaluacion;
import model.ExamenEscrito;
import model.Laboratorio;
import model.Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionesView extends JFrame {

    // COMPONENTES
    private JTextField txtNombreEvaluacion;
    private JTextField txtPonderacion;
    private JTextField txtNota;

    private JComboBox<String> cmbTipoEvaluacion;

    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JButton btnVolver;

    // TABLA
    private JTable tablaEvaluaciones;
    private DefaultTableModel modeloTabla;

    // LISTA
    private List<Evaluacion> listaEvaluaciones;

    public EvaluacionesView() {

        listaEvaluaciones = new ArrayList<>();

        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    /* =========================================
     * CONFIGURACIÓN
     * ========================================= */
    private void configurarVentana() {

        setTitle("EDUMANAGER DESKTOP - Evaluaciones");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    /* =========================================
     * COMPONENTES
     * ========================================= */
    private void inicializarComponentes() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        /* =========================================
         * TÍTULO
         * ========================================= */
        JLabel lblTitulo = new JLabel("Gestión de Evaluaciones");

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));

        lblTitulo.setForeground(new Color(10, 60, 80));

        lblTitulo.setBorder(
                BorderFactory.createEmptyBorder(0, 10, 25, 0)
        );

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        /* =========================================
         * CONTENIDO
         * ========================================= */
        JPanel panelContenido = new JPanel(new BorderLayout(25, 0));

        panelContenido.setBackground(new Color(245, 247, 250));

        /* =========================================
         * FORMULARIO
         * ========================================= */
        JPanel panelFormulario = new JPanel();

        panelFormulario.setBackground(new Color(15, 70, 90));

        panelFormulario.setLayout(new GridBagLayout());

        panelFormulario.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(10, 60, 80), 2
                        ),
                        BorderFactory.createEmptyBorder(
                                25, 25, 25, 25
                        )
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        /* =========================================
         * TITULO FORM
         * ========================================= */
        JPanel panelTituloFormulario = new JPanel();

        panelTituloFormulario.setLayout(
                new BoxLayout(
                        panelTituloFormulario,
                        BoxLayout.Y_AXIS
                )
        );

        panelTituloFormulario.setBackground(
                new Color(15, 70, 90)
        );

        JLabel lblRegistrar = new JLabel("Registrar");

        lblRegistrar.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        lblRegistrar.setForeground(Color.WHITE);

        JLabel lblEvaluacion = new JLabel("Evaluación");

        lblEvaluacion.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        lblEvaluacion.setForeground(Color.WHITE);

        panelTituloFormulario.add(lblRegistrar);
        panelTituloFormulario.add(lblEvaluacion);

        gbc.gridx = 0;
        gbc.gridy = 0;

        panelFormulario.add(panelTituloFormulario, gbc);

        /* =========================================
         * NOMBRE
         * ========================================= */
        gbc.gridy++;

        JLabel lblNombre = new JLabel("Nombre Evaluación");

        lblNombre.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        lblNombre.setForeground(Color.WHITE);

        panelFormulario.add(lblNombre, gbc);

        gbc.gridy++;

        txtNombreEvaluacion = new JTextField();

        txtNombreEvaluacion.setPreferredSize(
                new Dimension(180, 35)
        );

        txtNombreEvaluacion.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        panelFormulario.add(txtNombreEvaluacion, gbc);

        /* =========================================
         * TIPO
         * ========================================= */
        gbc.gridy++;

        JLabel lblTipo = new JLabel("Tipo de Evaluación");

        lblTipo.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        lblTipo.setForeground(Color.WHITE);

        panelFormulario.add(lblTipo, gbc);

        gbc.gridy++;

        cmbTipoEvaluacion = new JComboBox<>();

        cmbTipoEvaluacion.addItem("Examen Escrito");
        cmbTipoEvaluacion.addItem("Laboratorio");
        cmbTipoEvaluacion.addItem("Proyecto");

        cmbTipoEvaluacion.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        panelFormulario.add(cmbTipoEvaluacion, gbc);

        /* =========================================
         * PONDERACIÓN
         * ========================================= */
        gbc.gridy++;

        JLabel lblPonderacion = new JLabel("Ponderación (%)");

        lblPonderacion.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        lblPonderacion.setForeground(Color.WHITE);

        panelFormulario.add(lblPonderacion, gbc);

        gbc.gridy++;

        txtPonderacion = new JTextField();

        txtPonderacion.setPreferredSize(
                new Dimension(180, 35)
        );

        txtPonderacion.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        panelFormulario.add(txtPonderacion, gbc);

        /* =========================================
         * NOTA
         * ========================================= */
        gbc.gridy++;

        JLabel lblNota = new JLabel("Nota");

        lblNota.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        lblNota.setForeground(Color.WHITE);

        panelFormulario.add(lblNota, gbc);

        gbc.gridy++;

        txtNota = new JTextField();

        txtNota.setPreferredSize(
                new Dimension(180, 35)
        );

        txtNota.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        panelFormulario.add(txtNota, gbc);

        /* =========================================
         * BOTONES
         * ========================================= */
        gbc.gridy++;

        btnRegistrar = new JButton("Registrar Evaluación");

        btnLimpiar = new JButton("Limpiar");

        btnVolver = new JButton("Volver al Menú");

        JButton[] botones = {
                btnRegistrar,
                btnLimpiar,
                btnVolver
        };

        for (JButton boton : botones) {

            boton.setFocusPainted(false);

            boton.setFont(
                    new Font("Arial", Font.BOLD, 15)
            );

            boton.setPreferredSize(
                    new Dimension(200, 42)
            );

            boton.setCursor(
                    new Cursor(Cursor.HAND_CURSOR)
            );

            if (boton == btnRegistrar) {

                boton.setBackground(
                        new Color(144, 238, 144)
                );

            } else if (boton == btnLimpiar) {

                boton.setBackground(
                        new Color(173, 216, 230)
                );

            } else {

                boton.setBackground(
                        new Color(211, 211, 211)
                );
            }
        }

        JPanel panelBotones = new JPanel(
                new GridLayout(3, 1, 0, 10)
        );

        panelBotones.setBackground(
                new Color(15, 70, 90)
        );

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVolver);

        panelFormulario.add(panelBotones, gbc);

        /* =========================================
         * TABLA
         * ========================================= */
        JPanel panelTabla = new JPanel(new BorderLayout());

        panelTabla.setBackground(Color.WHITE);

        panelTabla.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 20, 20, 20
                        )
                )
        );

        JLabel lblTabla = new JLabel(
                "Evaluaciones Registradas"
        );

        lblTabla.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        lblTabla.setForeground(
                new Color(10, 60, 80)
        );

        panelTabla.add(lblTabla, BorderLayout.NORTH);

        String[] columnas = {
                "Nombre",
                "Tipo",
                "Ponderación",
                "Nota",
                "Puntaje Obtenido"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {

                return false;
            }
        };

        tablaEvaluaciones = new JTable(modeloTabla);

        tablaEvaluaciones.setRowHeight(32);

        tablaEvaluaciones.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        tablaEvaluaciones.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        JScrollPane scroll = new JScrollPane(
                tablaEvaluaciones
        );

        scroll.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 0, 0, 0
                )
        );

        panelTabla.add(scroll, BorderLayout.CENTER);

        /* =========================================
         * AGREGAR
         * ========================================= */
        panelFormulario.setPreferredSize(
                new Dimension(350, 0)
        );

        panelContenido.add(panelFormulario, BorderLayout.WEST);

        panelContenido.add(panelTabla, BorderLayout.CENTER);

        panelPrincipal.add(panelContenido, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    /* =========================================
     * EVENTOS
     * ========================================= */
    private void agregarEventos() {

        btnRegistrar.addActionListener(e -> {

            registrarEvaluacion();
            
        });
            
        btnLimpiar.addActionListener(e -> {

            limpiarCampos();
        });

        btnVolver.addActionListener(e -> {

            DashboardView dashboard = new DashboardView();

            dashboard.setVisible(true);

            dispose();
        });
    }

    /* =========================================
     * REGISTRAR
     * ========================================= */
    private void registrarEvaluacion() {

        String nombre =
                txtNombreEvaluacion.getText().trim();

        String tipo =
                cmbTipoEvaluacion
                        .getSelectedItem()
                        .toString();

        String ponderacionStr =
                txtPonderacion.getText().trim();

        String notaStr =
                txtNota.getText().trim();

        if (
                nombre.isEmpty()
                        || ponderacionStr.isEmpty()
                        || notaStr.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos son obligatorios.",
                    "Campos Vacíos",    
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        double ponderacion;
        double nota;

        try {

            ponderacion =
                    Double.parseDouble(ponderacionStr);

            nota =
                    Double.parseDouble(notaStr);

            if (
                    ponderacion <= 0
                            || ponderacion > 100
            ) {

                throw new NumberFormatException();
            }

            if (
                    nota < 0
                            || nota > 100
            ) {

                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese datos válidos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Evaluacion evaluacion;

        switch (tipo) {

            case "Examen Escrito":

                evaluacion = new ExamenEscrito(
                        nombre,
                        ponderacion,
                        nota
                );

                break;

            case "Laboratorio":

                evaluacion = new Laboratorio(
                        nombre,
                        ponderacion,
                        nota
                );

                break;

            default:

                evaluacion = new Proyecto(
                        nombre,
                        ponderacion,
                        nota
                );
        }

        listaEvaluaciones.add(evaluacion);

        Object[] fila = {
                evaluacion.getNombre(),
                tipo,
                evaluacion.getPonderacion() + "%",
                evaluacion.getNota(),
                String.format(
                        "%.2f",
                        evaluacion.puntajeObtenido()
                )
        };

        modeloTabla.addRow(fila);

        limpiarCampos();

        JOptionPane.showMessageDialog(
                this,
                "Evaluación registrada correctamente."
        );
    }

    /* =========================================
     * LIMPIAR
     * ========================================= */
    private void limpiarCampos() {

        txtNombreEvaluacion.setText("");

        txtPonderacion.setText("");

        txtNota.setText("");

        cmbTipoEvaluacion.setSelectedIndex(0);
    }
}
