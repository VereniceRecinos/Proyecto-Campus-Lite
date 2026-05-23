package view;

/**
 *
 * @author ALEJANDRO
 */

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
import javax.swing.Icon;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
public class Login extends JFrame{

    // Componentes
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    private JButton btnAceptar;
    private JButton btnCancelar;

    private JLabel lblError;
    private JLabel lblRecuperar;

    // Credenciales de prueba
    private final String USUARIO_CORRECTO = "admin";
    private final String PASSWORD_CORRECTA = "1234";

    public Login() {

        configurarVentana();
        inicializarComponentes();
        agregarEventos();
    }

    private void configurarVentana() {

        setTitle("EduManager - Login");
        setSize(520, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {

        /* =========================================
         * PANEL PRINCIPAL
         * ========================================= */
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelPrincipal.setBackground(new Color(245, 245, 245));

        /* =========================================
         * LOGO
         * ========================================= */
        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLogo.setBackground(new Color(245, 245, 245));

        Icon icono = UIManager.getIcon("OptionPane.informationIcon");

        JLabel lblLogo = new JLabel(icono);
        lblLogo.setPreferredSize(new Dimension(90, 90));

        panelLogo.add(lblLogo);

        /* =========================================
         * USUARIO
         * ========================================= */
        JLabel lblUsuario = new JLabel("Usuario/Código:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));

        txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(400, 45));
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));

        /* =========================================
         * PASSWORD
         * ========================================= */
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 16));

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(400, 45));
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));

        /* =========================================
         * ERROR
         * ========================================= */
        lblError = new JLabel("● Credenciales incorrectas");
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Arial", Font.PLAIN, 14));
        lblError.setVisible(false);

        /* =========================================
         * BOTONES
         * ========================================= */
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 20, 0));
        panelBotones.setMaximumSize(new Dimension(260, 45));
        panelBotones.setBackground(new Color(245, 245, 245));

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Arial", Font.BOLD, 18));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 18));
        
        
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);


        /* =========================================
         * FOOTER
         * ========================================= */
        JPanel panelFooter = new JPanel(new BorderLayout());
        panelFooter.setBackground(new Color(245, 245, 245));

        lblRecuperar = new JLabel("<HTML><U>Recuperar contraseña</U></HTML>");
        lblRecuperar.setForeground(Color.BLUE);
        lblRecuperar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblIdioma = new JLabel("🌐 ES");
        lblIdioma.setHorizontalAlignment(SwingConstants.RIGHT);

        panelFooter.add(lblRecuperar, BorderLayout.WEST);
        panelFooter.add(lblIdioma, BorderLayout.EAST);

        /* =========================================
         * VERSION Y ESTADO
         * ========================================= */
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        JLabel lblVersion = new JLabel("v4.2.0-STABLE");

        JLabel lblEstado = new JLabel("■ Conectado");
        lblEstado.setForeground(new Color(0, 150, 0));

        panelInferior.add(lblVersion, BorderLayout.WEST);
        panelInferior.add(lblEstado, BorderLayout.EAST);

        /* =========================================
         * AGREGAR COMPONENTES
         * ========================================= */
        panelPrincipal.add(panelLogo);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        panelPrincipal.add(lblUsuario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 8)));
        panelPrincipal.add(txtUsuario);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));

        panelPrincipal.add(lblPassword);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 8)));
        panelPrincipal.add(txtPassword);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        panelPrincipal.add(lblError);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 35)));

        JPanel panelCentroBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentroBotones.setBackground(new Color(245, 245, 245));
        panelCentroBotones.add(panelBotones);

        panelPrincipal.add(panelCentroBotones);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 40)));

        panelPrincipal.add(panelFooter);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void agregarEventos() {

        btnAceptar.addActionListener(e -> iniciarSesion());

        btnCancelar.addActionListener(e -> limpiarCampos());

        lblRecuperar.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                JOptionPane.showMessageDialog(
                        Login.this,
                        "Función de recuperación en desarrollo."
                );
            }
        });
    }

    /* =========================================
     * LÓGICA LOGIN
     * ========================================= */
    private void iniciarSesion() {

        String usuario = txtUsuario.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Campos Vacíos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (usuario.equals(USUARIO_CORRECTO)
                && password.equals(PASSWORD_CORRECTA)) {

            lblError.setVisible(false);

            // Ejecutamos la pantalla Estudiantes
        java.awt.EventQueue.invokeLater(() -> {
            PantallaEstudiantes ventana = new PantallaEstudiantes();
            ventana.setVisible(true);
        });
        
            dispose();

            // AQUÍ PUEDES ABRIR OTRA VENTANA
            // new DashboardView().setVisible(true);
            // dispose();

        } else {

            lblError.setVisible(true);

            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }

    private void limpiarCampos() {

        txtUsuario.setText("");
        txtPassword.setText("");

        lblError.setVisible(false);

        txtUsuario.requestFocus();
    }
}
