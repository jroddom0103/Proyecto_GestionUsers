package ui.frames;

import ui.panels.PanelLogin;

import javax.swing.*;

//Clase FrameLogin que hereda de la clase JFrame
public class FrameLogin extends JFrame {

    //Método constructor de la clase FrameLogin
    public FrameLogin() {

        //Definir tamaño
        this.setSize(600, 600);
        //Permitir cerrar mediante
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Establecer el título del frame
        this.setTitle("Proyecto Login");
        //Crear un icono para el frame a partir de una imagen
        ImageIcon image = new ImageIcon("ProyectoLogin\\src\\main\\resources\\images\\icono.png");
        //Establecer el icono del frame
        this.setIconImage(image.getImage());
        //Deshabilitar la función de redimensionar la ventana
        this.setResizable(false);

        //Declaración e inicialización de la clase PanelLogin que usa este frame
        PanelLogin p = new PanelLogin(this);
        //Se añade el panel al frame
        this.add(p);
        //Se declara visible
        this.setVisible(true);
    }
}