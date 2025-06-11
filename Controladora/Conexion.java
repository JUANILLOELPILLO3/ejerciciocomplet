package Controladora;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String url = "jdbc:mysql://localhost:3306/akihabara_db";
    private static final String usuario = "userAkihabara";
    private static final String contrasena = "Akihabara2025!";
    private Connection conexion;

    public static Connection getConexion() {
        try {
            Connection conn = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Base de datos conectada correctamente");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Se ha cerrado la conexión con la base de datos.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
 