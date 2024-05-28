package utils;

public class DBUtils {

    //CREDENCIALES DE LA BASE DE DATOS

    //Nombre de la base de datos
    public static final String DB_NAME = "dbapp";
    //Nombre del nombre del usuario que gestiona la base de datos
    public static final String USER = "root";
    //Nombre de la contraseña del usuario que gestiona la base de datos (Sin contraseña)
    public static final String PASS = "";

    //QUERYS DE LA APLICACIÓN

    //Consulta para comparar los usuarios con la id y la contraseña introducidas en PanelLogin
    public static String QUERY_LOGIN = "SELECT * FROM usuarios WHERE id=? AND pass = ?;";
    //Operación que inserta en la tabla usuarios los valores introducidos por el usuario en PanelAlta
    public static String QUERY_INSERT_USER = "INSERT INTO usuarios VALUES (?, ?, ?, ?)";
    //Operación que inserta en la tabla accesos el id del usuario y se establece de forma automática el número de accesos en 0
    public static String QUERY_INSERT_USER_IN_ACCESOS = "INSERT INTO accesos VALUES(?, 0);";
    //Operación que actualiza el número de accesos sumandole 1 cada vez que el usuario se logea con ese usuario
    public static String QUERY_UPDATE_NACCESOS = "UPDATE accesos SET nAccesos = nAccesos+1 WHERE idUsuario = ?;";
    //Consulta que sirve para obtener la información de un usuario mediante su id (PanelConsulta y PanelModificar)
    public static String QUERY_CHECK_USERS = "SELECT correo, pass, isAdmin FROM usuarios WHERE id=?";
    //Operación que actualiza la información introducida por el usuario para cambiar un usuario con una id (PanelModificar)
    public static String QUERY_UPDATE_USERS = "UPDATE usuarios SET correo = ?, pass = ?, isAdmin = ? WHERE id = ?";
    //Operación que borra un usuario de la tabla de usuarios mediante un id (PanelBaja)
    public static String QUERY_DELETE_USER = "DELETE FROM usuarios WHERE id = ?";
    //Operación que borra un usuario de la tabla de accesos mediante un id (PanelBaja). Se borra antes que el usuario en la tabla usuario por la restricción de la clave foránea
    public static String QUERY_DELETE_USER_IN_ACCESOS = "DELETE FROM accesos WHERE idUsuario=?";



}