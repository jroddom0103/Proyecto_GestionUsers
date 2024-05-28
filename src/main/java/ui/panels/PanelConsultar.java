package ui.panels;

import services.UserService;
import ui.frames.FrameLogin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelConsultar extends JPanel {

    // VINCULAMOS EL PANEL AL FRAME
    private FrameLogin framePadre;
    private JTextField user;
    private JButton bAtras;
    private JButton bConsultar;
    JTextArea areaInformacion;
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

    private MouseListener listenerConsultar = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            String id = user.getText();

            String infoUser = serviceUser.consultarUsuario(id);

            if (infoUser != null) {
                // Dividir la cadena y mostrar los datos en el área de texto
                areaInformacion.setText(""); // Limpiar el área de texto
                areaInformacion.append(id+"\n");
                String[] datos = infoUser.split(":");
                for (int i = 0;i<datos.length;i++) {
                    String dato = datos[i];
                    areaInformacion.append(dato.trim() + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(framePadre, "Usuario no encontrado.");
                areaInformacion.setText("");
            }
        }
    };

    MouseListener listenerMouse = new MouseAdapter() {

        public void mouseEntered(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(135, 206, 250)); // Fondo azul claro
            b.setBorder(new LineBorder(new Color(0, 115, 183), 3)); // Borde azul oscuro
        }

        public void mouseExited(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(102, 153, 204)); // Fondo azul medio
            b.setBorder(new LineBorder(new Color(135, 206, 250), 3)); // Borde azul claro
        }
    };
    private MouseListener listenerMouseOpciones = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            cargarPanelOpciones();
        }
    };

    public PanelConsultar(FrameLogin framePadre) {

        this.framePadre = framePadre;

        this.setBackground(new Color(0xDFDCDC));
        this.setLayout(null);

        JLabel panelCLabel = new JLabel("Panel CONSULTAR");
        panelCLabel.setBounds(240, 80, 172, 55);
        this.add(panelCLabel);

        JLabel usuario = new JLabel("Id:");
        usuario.setBounds(190, 170, 172, 55);
        this.add(usuario);

        user = new JTextField();
        user.setBounds(210, 188, 172, 20);
        this.add(user);

        areaInformacion = new JTextArea("");
        areaInformacion.setBounds(200,300,200,100);
        this.add(areaInformacion);
        areaInformacion.setEditable(false);

        bAtras = new JButton("Atras");
        bAtras.setBounds(40, 500, 100, 50);
        bAtras.setBackground(new Color(208, 223, 232));
        bAtras.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bAtras.addMouseListener(listenerMouse);
        bAtras.addMouseListener(listenerMouseOpciones);
        bAtras.addMouseListener(listenerMouseCambiarAspecto);
        this.add(bAtras);

        bConsultar = new JButton("Consultar");
        bConsultar.setBounds(240, 225, 100, 50);
        bConsultar.setBackground(new Color(208, 223, 232));
        bConsultar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        bConsultar.addMouseListener(listenerMouse);
        bConsultar.addMouseListener(listenerConsultar);
        bConsultar.addMouseListener(listenerMouseCambiarAspecto);
        this.add(bConsultar);
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

