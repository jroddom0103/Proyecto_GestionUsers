package utils;

public class DBUtils {

    //CREDENCIALES DE LA BASE DE DATOS
    public static final String DB_NAME = "dbapp";
    public static final String USER = "root";
    public static final String PASS = "";

    //QUERYS DE LA APLICACIÓN
    public static String QUERY_LOGIN = "SELECT * FROM usuarios WHERE id=? AND pass = ?;";
    public static String QUERY_INSERT_USER = "INSERT INTO usuarios VALUES (?, ?, ?, ?)";
    public static String QUERY_INSERT_USER_IN_ACCESOS = "INSERT INTO accesos VALUES(?, 0);";
    public static String QUERY_UPDATE_NACCESOS = "UPDATE accesos SET nAccesos = nAccesos+1 WHERE idUsuario = ?;";
    public static String QUERY_CHECK_USERS = "SELECT correo, pass, isAdmin FROM usuarios WHERE id=?";
    public static String QUERY_UPDATE_USERS = "UPDATE usuarios SET correo = ?, pass = ?, isAdmin = ? WHERE id = ?";
    public static String QUERY_DELETE_USER = "DELETE FROM usuarios WHERE id = ?";
    public static String QUERY_DELETE_USER_IN_ACCESOS = "DELETE FROM accesos WHERE idUsuario=?";



}