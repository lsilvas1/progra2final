import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MariaDBConnection {

    private static final String URL = "jdbc:mariadb://localhost:3306/bd_carro?permitMysqlScheme";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "abcd123";
    public static void getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el controlador JDBC" + e.toString());

        }

    }

}





