package services;

import java.time.LocalDateTime;

public class ServiceLogger {
    private GestionFicheroLogger gestion;
    private String ruta = "src/main/resources/loggers/logs.txt";

    public ServiceLogger() {
        this.gestion = new GestionFicheroLogger();
    }

    public void registrarLog(String infoUser, String accion, String estado) {
        LocalDateTime time = LocalDateTime.now();
        String mensaje = "["+accion.toUpperCase()+"] " + time.toString()+" "+infoUser +"-"+ estado;
        anadirFicheroLogger(mensaje);
    }

    public void anadirFicheroLogger(String mensaje) {
        this.gestion.anadirFichero(mensaje, this.ruta);
    }

}
