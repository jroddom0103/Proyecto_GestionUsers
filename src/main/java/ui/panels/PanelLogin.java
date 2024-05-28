package ui.panels;

import model.db.ConectarDB;
import services.ServiceLogger;
import services.UserService;
import ui.frames.FrameLogin;
import utils.DBUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Clase panel PanelLogin que hereda de la clase JPanel
public class PanelLogin extends JPanel {

    //Id del usuario
    private JTextField id;
    //Contraseña del usuario
    private JPasswordField pass;
    //Botón para enviar
    private JButton bEnviar;

    // Este es el FramePadre de este panel
    private FrameLogin framePadre;
    //Creación y declaración del objeto de la clase UserService
    private UserService serviceUser = new UserService();
    //Creación y declaración del objeto de la clase ServiceLogger
    private ServiceLogger serviceLogger = new ServiceLogger();

    //Creación y declaración de objeto MouseListener para estilos Hover
    private MouseListener listenerMouseCambiarAspecto = new MouseAdapter() {

        //Estilo para cuando se coloca el ratón por encima
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(135, 206, 250)); // Fondo azul claro
        }

        //Estilo para cuando se coloca fuera el ratón
        @Override
        public void mouseExited(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(208, 223, 232)); // Fondo azul medio
        }

        //Estilo para cuando se presiona el botón
        @Override
        public void mousePressed(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBorder(new LineBorder(new Color(50, 50, 50), 3));
        }

        //Estilo para cuando se deja de soltar el botón
        @Override
        public void mouseReleased(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        }
    };

    //Creación y declaración de objeto MouseListener para hacer funcionar el botón enviar
    private MouseListener listenerMouseEnviar = new MouseAdapter() {

        //Estilo para cuando se hace click encima del botón
        @Override
        public void mouseClicked(MouseEvent e) {

            //Se extrae el String del id del JTextField
            String idString = id.getText();
            //Se extrae el String de la contraseña del JTextField
            String passString = pass.getText();

            //Estructura condicional if que comprueba si el usuario se logea o no
            if (serviceUser.checkLogin(idString,passString)){
                //Llamada al método registrarLog
                serviceLogger.registrarLog(idString,"LOGIN","Correcta");
                //Llamada al método cargarPanelMainMenu()
                cargarPanelMainMenu();
            }else{
                //Llamada al método registrarLog
                serviceLogger.registrarLog(idString,"LOGIN","Incorrecta");
                //Creación de ventana mensaje error
                JOptionPane.showMessageDialog(framePadre, "Correo o pass equivocado.");
            }
        }
    };


    //Método constructor de la clase PanelLogin que recibe por parámetros el framePadre de la clase
    public PanelLogin(FrameLogin framePadre) {

        // Vinculamos this.framePadre con el framePadre que viene por parámetros
        this.framePadre = framePadre;
        //Se coloca un fondo
        this.setBackground(new Color(0xDFDCDC));
        this.setLayout(null);

        //Se crea un JLabel para el id
        JLabel correoLabel = new JLabel("Id: ");
        correoLabel.setLocation(new Point(160, 200));
        correoLabel.setSize(new Dimension(152, 32));
        //Se añade al panel
        this.add(correoLabel);

        //Se crea un JTextField para el id
        id = new JTextField("");
        id.setLocation(new Point(220, 200));
        id.setSize(new Dimension(152, 32));
        //Se añade al panel
        this.add(id);

        //Se crea un JLabel para la contraseña
        JLabel passwdLabel = new JLabel("Passwd: ");
        passwdLabel.setLocation(new Point(160, 250));
        passwdLabel.setSize(new Dimension(152, 32));
        //Se añade al panel
        this.add(passwdLabel);

        //Se crea un JPasswordField
        pass = new JPasswordField();
        pass.setLocation(new Point(220, 250));
        pass.setSize(new Dimension(152, 32));
        //Se añade al panel
        this.add(pass);

        //Se crea botón Enviar
        bEnviar = new JButton("Enviar");
        bEnviar.setLocation(new Point(190, 350));
        bEnviar.setSize(new Dimension(152, 32));
        bEnviar.setBackground(new Color(208, 223, 232)); // Fondo azul medio
        bEnviar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        //Se le aplica el listenerMouseEnviar
        bEnviar.addMouseListener(listenerMouseEnviar);
        //Se le aplica el listenerMouseCambiarAspecto
        bEnviar.addMouseListener(listenerMouseCambiarAspecto);
        //Se añade al panel
        this.add(bEnviar);

    }

    //Método que llama al panel principal
    public void cargarPanelMainMenu() {
        //Se elimina framePadre
        framePadre.remove(this);

        //Se añade el panelOpciones
        PanelOpciones panelOpciones = new PanelOpciones(framePadre);
        framePadre.add(panelOpciones);

        //Se vuelve a pintar el frame
        framePadre.repaint();
        framePadre.revalidate();
    }
}

