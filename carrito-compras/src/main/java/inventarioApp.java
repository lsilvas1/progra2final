import com.inventario.model.Producto;
import com.inventario.service.ProductoService;

import java.util.Scanner;

public class InventarioApp {
    private ProductoService productoService;
    private Scanner scanner;

    public InventarioApp() {
        productoService = new ProductoService();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        InventarioApp app = new InventarioApp();
        app.run();
    }

    public void run() {
        while (true) {
            System.out.println("Sistema de Inventarios:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Eliminar Producto");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Ver Todos los Productos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    actualizarProducto();
                    break;
                case 4:
                    verTodosLosProductos();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void agregarProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.next();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();
        System.out.print("Ingrese el precio: ");
        double precio = scanner.nextDouble();

        Producto producto = new Producto(0, nombre, cantidad, precio);
        productoService.agregarProducto(producto);
        System.out.println("Producto agregado con éxito.");
    }

    private void eliminarProducto() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        productoService.eliminarProducto(id);
        System.out.println("Producto eliminado con éxito.");
    }

    private void actualizarProducto() {
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = scanner.nextInt();
        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nombre = scanner.next();
        System.out.print("Ingrese la nueva cantidad: ");
        int cantidad = scanner.nextInt();
        System.out.print("Ingrese el nuevo precio: ");
        double precio = scanner.nextDouble();

        Producto producto = new Producto(id, nombre, cantidad, precio);
        productoService.actualizarProducto(producto);
        System.out.println("Producto actualizado con éxito.");
    }

    private void verTodosLosProductos() {
        System.out.println("Listado de Productos:");
        for (Producto producto : productoService.obtenerTodosLosProductos()) {
            System.out.println(producto);
        }
    }
}