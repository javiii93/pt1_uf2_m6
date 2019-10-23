package pt1_uf2_m6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Faccion {
	int faccion_id;
	String nombre_faccion, lore;

	public Faccion(int faccion_id, String nombre_faccion, String lore) {
		super();
		this.faccion_id = faccion_id;
		if (nombre_faccion.length() <= 15) {
			this.nombre_faccion = nombre_faccion;
		}
		if (lore.length() <= 200) {
			this.lore = lore;
		}
	}
	public void insert(Faccion faccion,String url) {
		String sql = "INSERT INTO Facciones(faccion_id,nombre_faccion,lore) VALUES(?,?,?)";
		 
        try (Connection conn = DriverManager.getConnection(url);
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	 pstmt.setInt(1, faccion.faccion_id);
             pstmt.setDouble(2, capacity);
             pstmt.setDouble(3, capacity);
             pstmt.executeUpdate();
        } 
      
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	public int getFaccion_id() {
		return faccion_id;
	}

	public void setFaccion_id(int faccion_id) {
		this.faccion_id = faccion_id;
	}

	public String getNombre_faccion() {
		return nombre_faccion;
	}

	public void setNombre_faccion(String nombre_faccion) {
		this.nombre_faccion = nombre_faccion;
	}

	public String getLore() {
		return lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

	@Override
	public String toString() {
		return "Faccion [faccion_id=" + faccion_id + ", nombre_faccion=" + nombre_faccion + ", lore=" + lore + "]";
	}

}
