package services;

import model.db.ConectarDB;
import utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // ATRIBUTOS
    private ServiceLogger logger;

    public UserService() {

        this.logger = new ServiceLogger();
    }


    public boolean checkLogin(String id, String pass){
        ConectarDB conectarDB = new ConectarDB(DBUtils.USER, DBUtils.PASS, DBUtils.DB_NAME);
        conectarDB.realizarConexion();

        String contrasenaCodificada = codificar(pass);

        try {
            // Comprueba si el usuario ha introducido correctamente sus credenciales
            PreparedStatement pst = conectarDB.obtenerConexion().prepareStatement(DBUtils.QUERY_LOGIN);
            pst.setString(1, id);
            pst.setString(2, contrasenaCodificada);

            ResultSet rs = pst.executeQuery();

            pst = conectarDB.obtenerConexion().prepareStatement(DBUtils.QUERY_UPDATE_NACCESOS);
            pst.setString(1,id);

            pst.executeUpdate();

            if (rs.next()) {
                System.out.println("Bienvenid@.");
                // Si se han introducido correctamente, se procede a cargar el panelOpciones
                return true;

            } else {
                return false;

            }

        } catch (SQLException r) {
            r.printStackTrace();
            return false;
        }finally {
            conectarDB.desconectarDB();
        }
    }

    public boolean agregarUser(String id, String correo, String contrasena, String contrasena2, String admin){

        boolean esAgregado = false;
        ConectarDB conectarDB = new ConectarDB(DBUtils.USER, DBUtils.PASS, DBUtils.DB_NAME);
        conectarDB.realizarConexion();

        boolean adminBoolean;
        if (admin.equals("Si")){
            adminBoolean = true;
        }else{
            adminBoolean = false;
        }

        if (!correo.matches("[a-zA-Z0-9._%+-]+@gmail\\.(es|com)")){
            return esAgregado;
        }

        if (correo.equals("") || contrasena.equals("")){
            return esAgregado;
        }

        if (id.length()>20 || correo.length()>20 || contrasena.length()>20) {
            return esAgregado;
        }

        if (!contrasena.equals(contrasena2)){
            return esAgregado;
        }

        try {

            String contrasenaCesar = codificar(contrasena);

            PreparedStatement pst = conectarDB.obtenerConexion().prepareStatement(DBUtils.QUERY_INSERT_USER);
            pst.setString(1,id);
            pst.setString(2,correo);
            pst.setString(3,contrasenaCesar);
            pst.setBoolean(4,adminBoolean);

            pst.executeUpdate();

            pst = conectarDB.obtenerConexion().prepareStatement(DBUtils.QUERY_INSERT_USER_IN_ACCESOS);
            pst.setString(1,id);

            pst.executeUpdate();
        }catch (SQLException r){
            r.printStackTrace();
            return esAgregado;
        }finally {
            conectarDB.desconectarDB();
        }

        esAgregado = true;


        return esAgregado;
    }

    public String consultarUsuario(String id){

        String infoUser = "No se pudo hacer la consulta.";
        ConectarDB conectarDB = new ConectarDB(DBUtils.USER, DBUtils.PASS, DBUtils.DB_NAME);
        conectarDB.realizarConexion();

        try {
            PreparedStatement pst = conectarDB.obtenerConexion().prepareStatement(DBUtils.QUERY_CHECK_USERS);
            pst.setString(1,id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                String correo = rs.getString("correo");
                String contrasena = rs.getString("pass");
                Boolean esAdmin = rs.getBoolean("isAdmin");
                infoUser = correo+":"+descodificar(contrasena)+":"+esAdmin;
            }


        }catch (SQLException r){
            r.printStackTrace();
        }finally {
            conectarDB.desconectarDB();
        }

        return infoUser;
    }

    public boolean modificarUsuario(String correo, String contrasena, boolean esAdmin, String id) {
        boolean resultado = false;
        ConectarDB conectarDB = new ConectarDB(DBUtils.USER, DBUtils.PASS, DBUtils.DB_NAME);
        conectarDB.realizarConexion();

        String contrasenaCodificada = codificar(contrasena);

        if (!correo.matches("[a-zA-Z0-9._%+-]+@gmail\\.(es|com)")){
            return resultado;
        }

        if (correo.equals("") || contrasenaCodificada.equals("")){
            return resultado;
        }

        if (id.toString().length()>20 || correo.toString().length()>20 || contrasenaCodificada.toString().length()>20){
            return resultado;
        }

        try {
            PreparedStatement pst = conectarDB.obtenerConexion().prepareStatement(DBUtils.QUERY_UPDATE_USERS);
            pst.setString(1, correo);
            pst.setString(2, contrasenaCodificada);
            pst.setBoolean(3, esAdmin);
            pst.setString(4,id);


            int columnas = pst.executeUpdate();
            if (columnas > 0){
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conectarDB.desconectarDB();
        }

        return resultado;
    }

    public boolean borrarUsuario(String id){

        boolean resultado = false;
        ConectarDB conectarDB = new ConectarDB(DBUtils.USER, DBUtils.PASS, DBUtils.DB_NAME);
        conectarDB.realizarConexion();

        try {
            PreparedStatement pst = conectarDB.obtenerConexion().prepareStatement(DBUtils.QUERY_DELETE_USER);
            pst.setString(1, id);


            int columnas = pst.executeUpdate();
            if (columnas > 0) {
                System.out.println("Usuario borrado exitosamente.");
                resultado = true;
            } else {
                System.out.println("No se encontró un usuario con ese ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conectarDB.desconectarDB();
        }

        return resultado;
    }

    public boolean borrarUsuarioAcceso(String id){
        boolean resultado = false;
        ConectarDB conectarDB = new ConectarDB(DBUtils.USER, DBUtils.PASS, DBUtils.DB_NAME);
        conectarDB.realizarConexion();

        try {
            PreparedStatement pst = conectarDB.obtenerConexion().prepareStatement(DBUtils.QUERY_DELETE_USER_IN_ACCESOS);
            pst.setString(1, id);


            int columnas = pst.executeUpdate();
            if (columnas > 0) {
                System.out.println("Usuario borrado exitosamente de accesos.");
                resultado = true;
            } else {
                System.out.println("No se encontró un usuario con ese ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conectarDB.desconectarDB();
        }

        return resultado;
    }


    public static String codificar(String contrasena) {
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String letras2 = "abcdefghijklmnñopqrstuvwxyz";
        String numeros = "1234567890";

        String textoCodificado = "";

        char caracter;
        for (int i = 0; i < contrasena.length(); i++) {
            caracter = contrasena.charAt(i);

            int pos = letras.indexOf(caracter);
            if (pos != -1) {
                textoCodificado += letras.charAt((pos + 7) % letras.length());
                continue;
            }

            int pos2 = letras2.indexOf(caracter);
            if (pos2 != -1) {
                textoCodificado += letras2.charAt((pos2 + 4) % letras2.length());
                continue;
            }

            int pos3 = numeros.indexOf(caracter);
            if (pos3 != -1) {
                textoCodificado += numeros.charAt((pos3 + 5) % numeros.length()); // Ajuste aquí
                continue;
            }

            textoCodificado += caracter;
        }

        return textoCodificado;
    }

    public static String descodificar(String contrasenaCesar){
        String letras="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String letras2="abcdefghijklmnñopqrstuvwxyz";
        String numeros="1234567890";

        String contrasenaDescodificada = "";

        char caracter;
        for (int i = 0; i<contrasenaCesar.length(); i++) {
            caracter = contrasenaCesar.charAt(i);

            int pos = letras.indexOf(caracter);
            if(pos != -1){
                contrasenaDescodificada += letras.charAt( (pos - 7 + letras.length()) % letras.length() );
                continue;
            }

            int pos2 = letras2.indexOf(caracter);
            if(pos2 != -1){
                contrasenaDescodificada += letras2.charAt( (pos2 - 4 + letras2.length()) % letras2.length() );
                continue;
            }

            int pos3 = numeros.indexOf(caracter);
            if(pos3 != -1){
                contrasenaDescodificada += numeros.charAt( (pos3 + 5 + numeros.length()) % numeros.length() );
                continue;
            }

            contrasenaDescodificada += caracter;
        }

        return contrasenaDescodificada;
    }



}