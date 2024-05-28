package services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GestionFicheroLogger {

    public void anadirFichero(String mensaje, String ruta) {

        // 1º Abrimos el fichero
        File fichero = new File(ruta);

        // 2º Comprobamos que existe
        if (fichero.exists() && fichero.isFile() && fichero.canWrite()) {

            FileWriter fw = null;
            BufferedWriter bw = null;

            try {
                // 3º Abrimos los flujos de escritura -> Append a true
                fw = new FileWriter(fichero, true);
                bw = new BufferedWriter(fw);


                // 4º 0peramos con el fichero
                bw.write(mensaje);
                bw.write("\n");


                // 5º Cerrar flujos
                bw.close();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
