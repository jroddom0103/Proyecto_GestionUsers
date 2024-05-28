package ui.panels;

import ui.frames.FrameLogin;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelOpciones extends JPanel {

    //Objeto de clase FrameLogin
    private FrameLogin framePadre;

    //Botones
    private JButton botonAlta, botonBaja, botonModificar, botonConsultar;

    //Colores
    private Color colorOriginalAlta, colorOriginalBaja, colorOriginalModificar, colorOriginalConsultar;

    //Mouse Listener para el aspecto de los botones
    private MouseListener listenerMouseCambiarAspecto = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            b.setBackground(new Color(135, 206, 250)); // Fondo azul claro
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            if (b == botonAlta) {
                b.setBackground(colorOriginalAlta);
            } else if (b == botonBaja) {
                b.setBackground(colorOriginalBaja);
            } else if (b == botonModificar) {
                b.setBackground(colorOriginalModificar);
            } else if (b == botonConsultar) {
                b.setBackground(colorOriginalConsultar);
            }
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

    //MouseListener para llamar al método cargarPanelAlta
    private MouseListener listenerMouseAlta = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Alta pulsado");
            cargarPanelAlta();
        }
    };

    //MouseListener para llamar al método cargarPanelBaja
    private MouseListener listenerMouseBaja = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Baja pulsado");
            cargarPanelBaja();
        }
    };

    //MouseListener para llamar al método cargarPanelModificar
    private MouseListener listenerMouseModificar = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Modificar pulsado");
            cargarPanelModificar();
        }
    };

    //MouseListener para llamar al método cargarPanelConsultar
    private MouseListener listenerMouseConsultar = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Consultar pulsado");
            cargarPanelConsultar();
        }
    };

    //Método constructor de la clase PanelOpciones
    public PanelOpciones(FrameLogin framePadre) {

        //frame padre
        this.framePadre = framePadre;
        this.setBackground(new Color(0xDFDCDC));
        this.setLayout(null);

        //Nombres de botones
        botonAlta = new JButton("Alta");
        botonBaja = new JButton("Baja");
        botonModificar = new JButton("Modificar");
        botonConsultar = new JButton("Consultar");

        //Color de fondo del panel y botones
        this.setBackground(new Color(0xDFDCDC));
        this.botonAlta.setBackground(new Color(0xC0DDB8));
        this.botonBaja.setBackground(new Color(0xEA8484));
        this.botonModificar.setBackground(new Color(0xD8B969));
        this.botonConsultar.setBackground(new Color(0x69B0D8));

        // Almacenar los colores originales
        colorOriginalAlta = botonAlta.getBackground();
        colorOriginalBaja = botonBaja.getBackground();
        colorOriginalModificar = botonModificar.getBackground();
        colorOriginalConsultar = botonConsultar.getBackground();

        //Establecer tamaño de botones
        this.botonAlta.setBounds(200, 100, 130, 32);
        this.botonBaja.setBounds(200, 200, 130, 32);
        this.botonModificar.setBounds(200, 300, 130, 32);
        this.botonConsultar.setBounds(200, 400, 130, 32);

        //Añadir cada botón a su ListenerMouse
        botonAlta.addMouseListener(listenerMouseAlta);
        botonBaja.addMouseListener(listenerMouseBaja);
        botonConsultar.addMouseListener(listenerMouseConsultar);
        botonModificar.addMouseListener(listenerMouseModificar);

        //Añadir los botones al ListenerMouse que cambia su estilo
        botonAlta.addMouseListener(listenerMouseCambiarAspecto);
        botonBaja.addMouseListener(listenerMouseCambiarAspecto);
        botonConsultar.addMouseListener(listenerMouseCambiarAspecto);
        botonModificar.addMouseListener(listenerMouseCambiarAspecto);

        //Se añaden los botones al panel
        this.add(botonAlta);
        this.add(botonBaja);
        this.add(botonModificar);
        this.add(botonConsultar);

    }

    //Se carga el panel de alta
    private void cargarPanelAlta() {
        // ELIMINAMOS THIS PanelLogin
        framePadre.remove(this);

        // AÑADIMOS UN PANEL ALTA AL ¡¡¡FRAME!!!
        PanelAlta panelAlta = new PanelAlta(framePadre);
        framePadre.add(panelAlta);

        // ULTIMO: REPINTAR EL FRAME
        framePadre.repaint();
        framePadre.revalidate();

    }

    //Se carga el panel de baja
    private void cargarPanelBaja() {
        // ELIMINAMOS THIS PanelLogin
        framePadre.remove(this);

        // AÑADIMOS UN PANEL ALTA AL ¡¡¡FRAME!!!
        PanelBaja panelBaja = new PanelBaja(framePadre);
        framePadre.add(panelBaja);

        // ULTIMO: REPINTAR EL FRAME
        framePadre.repaint();
        framePadre.revalidate();

    }

    //Se carga el panel de consultar
    private void cargarPanelConsultar() {
        //Eliminamos this de PanelConsultar
        framePadre.remove(this);

        //Añadimos PanelConsultar al frame
        PanelConsultar panelConsultar = new PanelConsultar(framePadre);
        framePadre.add(panelConsultar);

        //Se repinta el frame
        framePadre.repaint();
        framePadre.revalidate();

    }

    private void cargarPanelModificar() {
        // ELIMINAMOS THIS PanelLogin
        framePadre.remove(this);

        // AÑADIMOS UN PANEL ALTA AL ¡¡¡FRAME!!!
        PanelModificar panelModificar = new PanelModificar(framePadre);
        framePadre.add(panelModificar);

        // ULTIMO: REPINTAR EL FRAME
        framePadre.repaint();
        framePadre.revalidate();

    }
}