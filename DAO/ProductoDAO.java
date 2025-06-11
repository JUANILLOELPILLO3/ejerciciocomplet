package DAO;
import java.sql.*;
import java.util.Scanner;

import Controladora.Conexion;

public class ProductoDAO {
	Scanner a=new Scanner(System.in);
	public void verProductos() {
		Connection conexion=Conexion.getConexion();
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("select * from producto");
			while(rs.next()) {
				System.out.println("id: "+rs.getString("id") +"|| nombre: "+rs.getString("nombre")+ "||categoria: "+rs.getString("categoria")+"||precio_unidad: " +rs.getFloat("precio")+
						"|| Stock:"+rs.getInt("stock"));
			}
			stmt.close();
			conexion.close();
			} catch (SQLException e) {
			System.out.println("Error al conectar la base de datos: " +
			e.getMessage());
			}
	}
	
	public void verProductoID() {
	    Connection conexion = Conexion.getConexion();
	    System.out.println("Introduce el id que quieres buscar:");
	    int id = Integer.parseInt(a.nextLine());

	    try {
	        Statement stmt = conexion.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM producto WHERE id=" + id);
	        if (rs.next()) {
	            System.out.println("id: " + rs.getInt("id") +" || nombre: " + rs.getString("nombre") +" || categoria: " + rs.getString("categoria") +
	                               " || precio_unidad: " + rs.getFloat("precio") +" || Stock: " + rs.getInt("stock"));
	        } else {
	            System.out.println("No se encontró ningún producto con el ID: " + id);
	        }
	        stmt.close();
	        conexion.close();
	    } catch (SQLException e) {
	        System.out.println("Error al consultar el producto: " + e.getMessage());
	    }
	}
	
	
	public void agregarProducto() {
	    Connection conexion = Conexion.getConexion();
	    try {
	        System.out.println("Introduce el nombre del producto:");
	        String nombre = a.nextLine();
	        System.out.println("Introduce la categoría (Figura, Manga, Póster, Llavero, Ropa):");
	        String categoria = a.nextLine();
	        System.out.println("Introduce el precio:");
	        float precio = Float.parseFloat(a.nextLine());
	        System.out.println("Introduce el stock:");
	        int stock = Integer.parseInt(a.nextLine());
	        String sql = "INSERT INTO producto (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
	        PreparedStatement stmt = conexion.prepareStatement(sql);
	        stmt.setString(1, nombre);
	        stmt.setString(2, categoria);
	        stmt.setFloat(3, precio);
	        stmt.setInt(4, stock);
	        stmt.executeUpdate();
	        System.out.println("Producto insertado correctamente.");
	        stmt.close();
	        conexion.close();
	    } catch (SQLException e) {
	        System.out.println("Error al insertar producto: " + e.getMessage());
	    }

	}
	
	public boolean actualizarProducto() {
	    Connection conexion = Conexion.getConexion();
	    boolean actualizado = false;
	    System.out.println("Introduce el id que quieres buscar:");
	    int id = Integer.parseInt(a.nextLine());
	    try {
	    	String sql = "SELECT * FROM producto WHERE id = ?";
	    	PreparedStatement stmt = conexion.prepareStatement(sql);
	    	stmt.setInt(1, id);
	    	ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            System.out.println("Introduce el nuevo nombre:");
	            String nombre = a.nextLine();
	            System.out.println("Introduce la nueva categoría:");
	            String categoria = a.nextLine();
	            System.out.println("Introduce el nuevo precio:");
	            float precio = Float.parseFloat(a.nextLine());
	            System.out.println("Introduce el nuevo stock:");
	            int stock = Integer.parseInt(a.nextLine());
	            String update = "UPDATE producto SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?";
	            PreparedStatement utmt = conexion.prepareStatement(update);
	            utmt.setString(1, nombre);
	            utmt.setString(2, categoria);
	            utmt.setFloat(3, precio);
	            utmt.setInt(4, stock);
	            utmt.executeUpdate();
	            utmt.close();
	            actualizado = true;
	            System.out.println("Producto actualizado correctamente.");
	        } else {
	            System.out.println("No se encontró un producto con el ID: ");
	        }
	        rs.close();
	        stmt.close();
	        conexion.close();
	    } catch (SQLException e) {
	        System.out.println("Error al actualizar el producto: " + e.getMessage());
	    }

	    return actualizado;
	}
	public void eliminarProducto() {
	    Connection conexion = Conexion.getConexion();
	    try {
	        System.out.println("Introduce el id que quieres eliminar:");
	        String id = a.nextLine();

	        String sql = "SELECT * FROM producto WHERE id = ?";
	        PreparedStatement stmt = conexion.prepareStatement(sql);
	        stmt.setString(1, id);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            String eliminar1 = "DELETE FROM producto WHERE id = ?";
	            PreparedStatement dstmt = conexion.prepareStatement(eliminar1);
	            dstmt.setString(1, id);
	            dstmt.executeUpdate();

	            System.out.println("Producto eliminado correctamente.");
	            dstmt.close();
	        } else {
	            System.out.println("No se encontró ningún producto con ese id.");
	        }

	        stmt.close();
	        conexion.close();
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar producto: " + e.getMessage());
	    }
	}
	
	public void verProductoNombre() {
	    Connection conexion = Conexion.getConexion();
	    System.out.println("Introduce el nombre que quieres buscar:");
	    String nombre = a.nextLine();
	    try {
	    	String sql = "SELECT * FROM producto WHERE nombre = ?";
	    	PreparedStatement stmt = conexion.prepareStatement(sql);
	    	stmt.setString(1, nombre);
	    	ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            System.out.println("id: " + rs.getInt("id") +" || nombre: " + rs.getString("nombre") +" || categoria: " + rs.getString("categoria") +
	                               " || precio_unidad: " + rs.getFloat("precio") +" || Stock: " + rs.getInt("stock"));
	        } else {
	            System.out.println("No se encontró ningún producto con ese nombre ");
	        }
	        stmt.close();
	        conexion.close();
	    } catch (SQLException e) {
	        System.out.println("Error al consultar el producto: " + e.getMessage());
	    }
	}
	
	public void verProductoCategoria() {
		Connection conexion = Conexion.getConexion();
	    System.out.println("Introduce la categoría que quieres buscar:");
	    String categoria = a.nextLine();
	    try {
	        String sql = "SELECT * FROM producto WHERE categoria = ?";
	        PreparedStatement stmt = conexion.prepareStatement(sql);
	        stmt.setString(1, categoria);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            System.out.println("id: " + rs.getInt("id") +" || nombre: " + rs.getString("nombre") +" || categoria: " + rs.getString("categoria") +
	                               " || precio_unidad: " + rs.getFloat("precio") +" || Stock: " + rs.getInt("stock"));
	        } else {
	            System.out.println("No se encontró ningún producto con esa categoria ");
	        }
	        stmt.close();
	        conexion.close();
	    } catch (SQLException e) {
	        System.out.println("Error al consultar el producto: " + e.getMessage());
	    }
	}
	public void agregarProductoIA() {
	    Connection conexion = Conexion.getConexion();
	    try {
	        System.out.println("Introduce el tipo de producto (ej. figura):");
	        String tipo = a.nextLine();
	        System.out.println("Introduce la franquicia (ej. Naruto):");
	        String franquicia = a.nextLine();
	        IA.Llm servicio = new IA.Llm();
	        String nombre = servicio.sugerirNombreProducto(tipo, franquicia);
	        System.out.println("Nombre sugerido por la IA: " + nombre);
	        System.out.println("Introduce el precio:");
	        float precio = Float.parseFloat(a.nextLine());
	        System.out.println("Introduce el stock:");
	        int stock = Integer.parseInt(a.nextLine());
	        String sql = "INSERT INTO producto (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
	        PreparedStatement stmt = conexion.prepareStatement(sql);
	        stmt.setString(1, nombre);
	        stmt.setString(2, tipo);
	        stmt.setFloat(3, precio);
	        stmt.setInt(4, stock);
	        stmt.executeUpdate();

	        System.out.println("Producto insertado correctamente con nombre sugerido por IA.");
	        stmt.close();
	        conexion.close();

	    } catch (Exception e) {
	        System.out.println("Error al insertar producto con IA: " + e.getMessage());
	    }
	}

	
	

}
	
	

	
	

		


	
