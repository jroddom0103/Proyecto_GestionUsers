package ui.panels;

import services.ServiceLogger;
import services.UserService;
import ui.frames.FrameLogin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelBaja extends JPanel {

    // VINCULAMOS EL PANEL AL FRAME
    private FrameLogin framePadre;
    private JTextField idIntro;
    private JTextField correoField;
    private JTextField pass;
    private JComboBox isAdmin;
    private JButton bConsultar;
    private JButton bBorrar;
    private JButton bAtras;
    private UserService serviceUser = new UserService();


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
            b.setBorder(new LineBorder(new Color(0, 0, 0), 2)); // Borde negro
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


    private MouseListener listenerMouseConsultar = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            String id = idIntro.getText();
            String infoUser = serviceUser.consultarUsuario(id);
            String[] datos = infoUser.split(":");
            if (datos.length == 3) {
                correoField.setText(datos[0].trim());
                pass.setText(datos[1].trim());
                isAdmin.setSelectedItem(datos[2].trim().equals("true") ? "Si" : "No");
            } else {
                JOptionPane.showMessageDialog(framePadre, "Error al recuperar los datos del usuario.");
                limpiarCampos();
            }


        }
    };

    private MouseListener listenerMouseBorrar = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (serviceUser.borrarUsuarioAcceso(idIntro.getText())){
                if (serviceUser.borrarUsuario(idIntro.getText())){
                    JOptionPane.showMessageDialog(framePadre, "Usuario borrado correctamente.");
                }else{
                    JOptionPane.showMessageDialog(framePadre, "El usuario con esa ID no existe.");
                }
            }
        }
    };

    private MouseListener listenerMouseOpciones = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            cargarPanelOpciones();
        }
    };


    public PanelBaja(FrameLogin framePadre) {

        this.framePadre = framePadre;

        this.setBackground(new Color(0xDFDCDC));
        this.setLayout(null);

        JLabel eti = new JLabel("Panel Baja");
        eti.setBounds(270, 30, 172, 55);
        this.add(eti);

        JLabel idIntroducir = new JLabel("Id:");
        idIntroducir.setBounds(190, 80, 172, 55);
        this.add(idIntroducir);

        idIntro = new JTextField();
        idIntro.setBounds(220, 100, 172, 20);
        this.add(idIntro);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(175, 230, 172, 55);
        this.add(correoLabel);

        correoField = new JTextField();
        correoField.setBounds(240, 250, 172, 20);
        this.add(correoField);
        correoField.setEditable(false);


        JLabel contrasenaLabel = new JLabel("Contrasena:");
        contrasenaLabel.setBounds(155, 280, 172, 55);
        this.add(contrasenaLabel);

        pass = new JTextField();
        pass.setBounds(240, 300, 172, 20);
        this.add(pass);
        pass.setEditable(false);

        JLabel esAdminLabel = new JLabel("Es admin:");
        esAdminLabel.setBounds(170, 330, 172, 55);
        this.add(esAdminLabel);

        isAdmin = new JComboBox<>();
        isAdmin.addItem("Si");
        isAdmin.addItem("No");
        isAdmin.setBounds(240, 350, 150, 20);
        this.add(isAdmin);
        isAdmin.setEnabled(false);

        bAtras = new JButton("Atras");
        bAtras.setBounds(40, 500, 100, 50);
        bAtras.setBackground(new Color(208, 223, 232));
        bAtras.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bAtras.addMouseListener(listenerMouseOpciones);
        bAtras.addMouseListener(listenerMouseCambiarAspecto);
        this.add(bAtras);

        bConsultar = new JButton("Consultar");
        bConsultar.setBounds(250, 150, 100, 50);
        bConsultar.setBackground(new Color(208, 223, 232));
        bConsultar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bConsultar.addMouseListener(listenerMouseConsultar);
        bConsultar.addMouseListener(listenerMouseCambiarAspecto);
        this.add(bConsultar);

        bBorrar = new JButton("Eliminar");
        bBorrar.setBounds(250, 400, 100, 50);
        bBorrar.setBackground(new Color(208, 223, 232));
        bBorrar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bBorrar.addMouseListener(listenerMouseBorrar);
        bBorrar.addMouseListener(listenerMouseCambiarAspecto);
        this.add(bBorrar);

    }

    private void limpiarCampos() {
        correoField.setText("");
        pass.setText("");
        isAdmin.setSelectedItem("No");
    }

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
