package ui.panels;

import services.UserService;
import ui.frames.FrameLogin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelModificar extends JPanel {

    private FrameLogin framePadre;
    private JTextField idIntro;
    private JTextField correoField;
    private JTextField pass;
    private JComboBox<String> isAdmin;
    private JButton bAtras;
    private JButton bConsultar;
    private JButton bModificar;
    private UserService serviceUser = new UserService();

    private MouseListener listenerMouseOpciones = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            cargarPanelOpciones();
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

    private MouseListener listenerMouseModificar = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            String id = idIntro.getText();
            String correo = correoField.getText();
            String contrasena = pass.getText();
            boolean admin;
            if (isAdmin.getSelectedItem().equals("Si")){
                admin=true;
            }else{
                admin=false;
            }
            boolean resultado = serviceUser.modificarUsuario(correo, contrasena, admin, id);
            if (resultado) {
                JOptionPane.showMessageDialog(framePadre, "Usuario modificado.");
                limpiarCampos();
            } else if (id.toString().length()>20 || correo.toString().length()>20 || contrasena.toString().length()>20){
                JOptionPane.showMessageDialog(framePadre, "No se puden poner mas de 20 caracteres en los campos.");
            }else if (correo.matches("[a-zA-Z0-9._%+-]+@gmail\\.(es|com)")){
                JOptionPane.showMessageDialog(framePadre, "El formato del correo no es correcto.");
            }else if (correo.equals("") || contrasena.equals("")){
                JOptionPane.showMessageDialog(framePadre, "Todos los campos deben estar rellenados.");
            }else {
                JOptionPane.showMessageDialog(framePadre, "El correo debe seguir un formato correcto.");
            }
        }
    };

    public PanelModificar(FrameLogin framePadre) {
        this.framePadre = framePadre;

        this.setBackground(new Color(0xDFDCDC));
        this.setLayout(null);

        JLabel eti = new JLabel("Panel Modificar");
        eti.setBounds(253, 30, 172, 55);
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

        JLabel contrasenaLabel = new JLabel("Contrasena:");
        contrasenaLabel.setBounds(155, 280, 172, 55);
        this.add(contrasenaLabel);

        pass = new JTextField();
        pass.setBounds(240, 300, 172, 20);
        this.add(pass);

        JLabel esAdminLabel = new JLabel("Es admin:");
        esAdminLabel.setBounds(170, 330, 172, 55);
        this.add(esAdminLabel);

        isAdmin = new JComboBox<>();
        isAdmin.addItem("Si");
        isAdmin.addItem("No");
        isAdmin.setBounds(240, 350, 150, 20);
        this.add(isAdmin);

        bAtras = new JButton("Atras");
        bAtras.setBounds(40, 500, 100, 50);
        bAtras.setBackground(new Color(208, 223, 232));
        bAtras.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bAtras.addMouseListener(listenerMouseOpciones);
        this.add(bAtras);

        bConsultar = new JButton("Consultar");
        bConsultar.setBounds(250, 150, 100, 50);
        bConsultar.setBackground(new Color(208, 223, 232));
        bConsultar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bConsultar.addMouseListener(listenerMouseConsultar);
        this.add(bConsultar);

        bModificar = new JButton("Modificar");
        bModificar.setBounds(250, 400, 100, 50);
        bModificar.setBackground(new Color(208, 223, 232));
        bModificar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bModificar.addMouseListener(listenerMouseModificar);
        this.add(bModificar);
    }

    private void limpiarCampos() {
        correoField.setText("");
        pass.setText("");
        isAdmin.setSelectedItem("No");
    }

    private void cargarPanelOpciones() {
        framePadre.remove(this);

        PanelOpciones panelOpciones = new PanelOpciones(framePadre);
        framePadre.add(panelOpciones);

        framePadre.repaint();
        framePadre.revalidate();
    }
}
