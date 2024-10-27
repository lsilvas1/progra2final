import com.inventario.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private final String url = "jdbc:mariadb://localhost:3306/inventario_db";
    private final String user = "root"; // usuario de DB
    private final String password = "abde123"; // contraseña de usuario de bd
    public ProductoDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");  //driver utilizado para la conexion
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void agregarProducto(Producto producto) {
        String query = "INSERT INTO productos (nombre, cantidad, precio) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, producto.getNombre());
            pst.setInt(2, producto.getCantidad());
            pst.setDouble(3, producto.getPrecio());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int id) {
        String query = "DELETE FROM productos WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto(Producto producto) {
        String query = "UPDATE productos SET nombre = ?, cantidad = ?, precio = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, producto.getNombre());
            pst.setInt(2, producto.getCantidad());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");
                productos.add(new Producto(id, nombre, cantidad, precio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public Producto obtenerProductoPorId(int id) {
        String query = "SELECT * FROM productos WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad");
                    double precio = rs.getDouble("precio");
                    return new Producto(id, nombre, cantidad, precio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}