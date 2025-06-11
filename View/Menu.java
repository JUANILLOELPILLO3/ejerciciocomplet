package View;
import DAO.ProductoDAO;
import java.util.Scanner;
public class Menu {
	public void mostrarMenu() {
	ProductoDAO a = new ProductoDAO();
	Scanner p =new Scanner(System.in);
	while (true) { 
		System.out.println("Bienvenido al menu principal"); 
		System.out.println("Elige una opcion: "); 
		System.out.println("1. Añadir producto."); 
		System.out.println("2.Consultar producto por ID."); 
		System.out.println("3. Listar todos los productos."); 
		System.out.println("4. Listar productos por nombre."); 
		System.out.println("5. Listar productos por categoría."); 
		System.out.println("6. Actualizar producto."); 
		System.out.println("7. Eliminar producto."); 
		System.out.println("8. Agregar producto con IA."); 
		System.out.println("9. Salir del programa."); 

		int opcion=Integer.parseInt(p.nextLine()); 
		if (opcion==1) { 
			a.agregarProducto();
		}else if(opcion==2) {
			a.verProductoID();
		}else if (opcion==3) { 
			a.verProductos();
		}else if(opcion==4) { 
			a.verProductoNombre();
		}else if (opcion==5) {
			a.verProductoCategoria();
		}else if(opcion==6) {
			a.actualizarProducto();
		}else if (opcion==7) {
			a.eliminarProducto();
		}else if (opcion==8) {
			a.agregarProductoIA();
		}else if (opcion==8){
			System.out.println("gracias por su visita");
		}
		else {
			System.out.println("opcion no valida");
		}
		
		}
	}
}