package ui.panels;

import services.ServiceLogger;
import services.UserService;
import ui.frames.FrameLogin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

//Clase panel PanelAlta que hereda de la clase JPanel
public class PanelAlta extends JPanel implements ItemListener {

    //Campos de texto para la entrada de datos
    JTextField textFieldId;
    JTextField textFieldCorreo;
    JTextField textFieldPass;
    JTextField textFieldPass2;
    boolean esAdmin;
    private JComboBox<String> comboAdmin;

    //Botones
    JButton b;
    JButton bAtras;
    JButton bLogin;

    //Objetos de clases
    private ServiceLogger serviceLogger = new ServiceLogger();

    // Este es el FramePadre de este panel
    private FrameLogin framePadre;

    //Creación y declaración del objeto de la clase UserService
    UserService serviceUser = new UserService();

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource()==comboAdmin) {
            String seleccionado=(String)comboAdmin.getSelectedItem();
            if (seleccionado.equals("Si")){
                this.esAdmin = true;
            }else{
                this.esAdmin = false;
            }
        }
    }

    //Listener para el botón de alta
    MouseListener listenerAlta = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {

            //Método para agregar usuarios comprobando cualquier posible problema de formato
            if (serviceUser.agregarUser(textFieldId.getText(),textFieldCorreo.getText(),textFieldPass.getText(), textFieldPass2.getText(),comboAdmin.getSelectedItem().toString())){
                JOptionPane.showMessageDialog(framePadre, "Usuario introducido.");
                serviceLogger.registrarLog(textFieldId.getText(),"ALTA","Correcta");
            }else if (textFieldId.getText().length()>20 || textFieldCorreo.getText().length()>20 || textFieldPass.getText().length()>20){
                JOptionPane.showMessageDialog(framePadre, "No se puden introducir mas de 20 caracteres en un campo.");
                serviceLogger.registrarLog(textFieldId.getText(),"ALTA","Incorrecta");
            }else if (!textFieldCorreo.getText().matches("[a-zA-Z0-9._%+-]+@gmail\\.(es|com)")){
                JOptionPane.showMessageDialog(framePadre, "El formato del correo no es correcto");
                serviceLogger.registrarLog(textFieldId.getText(),"ALTA","Incorrecta");
            }else if (textFieldCorreo.equals("") || textFieldPass.equals("")){
                JOptionPane.showMessageDialog(framePadre, "Todos los campos deben estar rellenados.");
                serviceLogger.registrarLog(textFieldId.getText(),"ALTA","Incorrecta");
            }else if (!textFieldPass.equals(textFieldPass2)){
                JOptionPane.showMessageDialog(framePadre, "Las contrasenas no coinciden.");
                serviceLogger.registrarLog(textFieldId.getText(),"ALTA","Incorrecta");
            }else {
                JOptionPane.showMessageDialog(framePadre, "Ya hay un usuario con esa id.");
                serviceLogger.registrarLog(textFieldId.getText(),"ALTA","Incorrecta");
            }
        }
    };

    //Listener para cambiar los estilos de los botones cuando se pasa el ratón por encima
    private MouseListener listenerMouseCambiarAspecto = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(135, 206, 250)); // Fondo azul claro
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(208, 223, 232)); // Fondo azul medio
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBorder(new LineBorder(new Color(50, 50, 50), 3));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        }
    };

    //Listener para el botón atrás que carga el panel de opciones
    private MouseListener listenerMouseOpciones = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            cargarPanelOpciones();
        }
    };

    public PanelAlta(FrameLogin framePadre){

        this.framePadre = framePadre;
        this.setBackground(new Color(174, 139, 225));
        this.setLayout(null);

        //Se crea un JLabel para el panel
        JLabel panelCLabel = new JLabel("Panel ALTA");
        panelCLabel.setBounds(240, 60, 172, 55);
        this.add(panelCLabel);

        //Se crea un JLabel para el id
        JLabel idusuario = new JLabel("ID: ");
        idusuario.setLocation(new Point(230,130));
        idusuario.setSize(new Dimension(152,32));
        this.add(idusuario);

        //Se crea un JTextField para el id
        textFieldId = new JTextField("");
        textFieldId.setLocation(new Point(260,130));
        textFieldId.setSize(new Dimension(152,32));
        this.add(textFieldId);

        //Se crea un JLabel para el correo
        JLabel nombre = new JLabel("Correo: ");
        nombre.setLocation(new Point(200,170));
        nombre.setSize(new Dimension(152,32));
        this.add(nombre);

        //Se crea un JTextField para el correo
        textFieldCorreo = new JTextField("");
        textFieldCorreo.setLocation(new Point(260,170));
        textFieldCorreo.setSize(new Dimension(152,32));
        this.add(textFieldCorreo);

        //Se crea un JLabel para la contraseña
        JLabel contrasena = new JLabel("Contrasena: ");
        contrasena.setLocation(new Point(180,210));
        contrasena.setSize(new Dimension(152,32));
        this.add(contrasena);

        //Se crea un JTextField para la contraseña
        textFieldPass = new JTextField("");
        textFieldPass.setLocation(new Point(260,210));
        textFieldPass.setSize(new Dimension(152,32));
        this.add(textFieldPass);

        //Se crea un JLabel para confirmar la contraseña
        JLabel contrasena2 = new JLabel("Confirmar contrasena:");
        contrasena2.setLocation(new Point(120,250));
        contrasena2.setSize(new Dimension(152,32));
        this.add(contrasena2);

        //Se crea un JTextField para repetir la contraseña
        textFieldPass2 = new JTextField("");
        textFieldPass2.setLocation(new Point(260,250));
        textFieldPass2.setSize(new Dimension(152,32));
        this.add(textFieldPass2);

        //Se crea un JLabel para esAdmin
        JLabel esAdmin = new JLabel("Ser administrador");
        esAdmin.setLocation(new Point(150,300));
        esAdmin.setSize(new Dimension(152,32));
        this.add(esAdmin);

        //Se utiliza JComboBox para poder selecionar opciones
        setLayout(null);
        comboAdmin = new JComboBox<String>();
        comboAdmin.setBounds(270,300,100,32);
        add(comboAdmin);
        comboAdmin.addItem("Si");
        comboAdmin.addItem("No");

        //Se crea un botón para enviar
        b = new JButton("Enviar");
        b.setLocation(new Point(220,400));
        b.setSize(new Dimension(152,32));
        b.addMouseListener(listenerMouseCambiarAspecto);
        b.addMouseListener(listenerAlta);
        this.add(b);

        //Se crea un botón para volver al menú de opciones
        bAtras = new JButton("Atras");
        bAtras.setBounds(40, 500, 100, 50);
        bAtras.setBackground(new Color(208, 223, 232));
        bAtras.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bAtras.addMouseListener(listenerMouseCambiarAspecto);
        bAtras.addMouseListener(listenerMouseOpciones);
        this.add(bAtras);

    }

    //Se crea un método para cargar el panel de opciones
    private void cargarPanelOpciones() {
        // ELIMINAMOS THIS PanelLogin
        framePadre.remove(this);

        // AÑADIMOS UN PANEL ALTA AL ¡¡¡FRAME!!!
        PanelOpciones panelOpciones = new PanelOpciones(framePadre);
        framePadre.add(panelOpciones);

        // ULTIMO: REPINTAR EL FRAME
        framePadre.repaint();
        framePadre.revalidate();
    }

}

