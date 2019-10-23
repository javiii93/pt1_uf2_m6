package pt1_uf2_m6;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
	static ArrayList<Faccion> facciones;
	static ArrayList<Personaje> personajes;
	//la ruta(url) debe existir
	static String sql, url = "jdbc:sqlite:C:\\sqlite\\java";;
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		facciones = new ArrayList<>();
		personajes = new ArrayList<>();
		Faccion caballeros = new Faccion(1, "Caballeros",
				"Los caballeros de Ashfeld son paradigmas del poder. La Legión de Hierro los envió para llevar la paz a esas tierras y, desde entonces, disfrutan de la libertad y han hecho de Ashfeld su hogar. Creen que muchas (si no todas) de las antiguas ruinas que cubren sus tierras fueron construidas por el Gran Imperio, precursor de la Legion de Hierro.");
		Faccion vikingos = new Faccion(2, "Vikingos",
				"Los vikingos desaparecieron hace siglos, tras escapar de sus destrozadas tierras natales en pos de costas desconocidas. Los caballeros conquistaron a aquellos que se quedaron atras e hicieron que se incorporaran a su cultura. ");
		Faccion samuráis = new Faccion(3, "Samuráis",
				"La historia no ha tratado bien a los samurais. Originarios de una tienda allende los mares, cuentan la historia de un emperador y una patria que desaparecieron entre el mar y el fuego. Casi un milenio después, la nación nomada ha dejado de errar y ha construido un nuevo imperio cerca de las tierras reclamadas por los vikingos y por las que disputan los caballeros.");
		facciones.add(caballeros);
		facciones.add(vikingos);
		facciones.add(samuráis);
		Personaje p1 = new Personaje(1, "manuel", 112, 322, 1);
		personajes.add(p1);
		Personaje p2 = new Personaje(2, "raul", 110, 320, 1);
		personajes.add(p2);
		Personaje p3 = new Personaje(3, "enric", 321, 23, 1);
		personajes.add(p3);
		Personaje p4 = new Personaje(4, "sara", 456, 989, 2);
		personajes.add(p4);
		Personaje p5 = new Personaje(5, "vane", 23, 586, 2);
		personajes.add(p5);
		Personaje p6 = new Personaje(6, "lore", 89, 76, 3);
		personajes.add(p6);
		Personaje p7 = new Personaje(7, "jacob", 45, 65, 3);
		personajes.add(p7);
		
		connect();
		createNewDatabase("forHonor.db");
		sql="CREATE TABLE IF NOT EXISTS Faccion (\n"
                + "    faccion_id integer PRIMARY KEY,\n"
                + "    nombre_faccion VARCHAR(15) NOT NULL,\n"
                + "    lore  VARCHAR(200)\n"
                + ");";
		createNewTable(sql);
		sql="CREATE TABLE IF NOT EXISTS Personaje (\n"
                + "    personaje_id integer PRIMARY KEY,\n"
                + "    nombre_personaje VARCHAR(15) NOT NULL,\n"
                + "    ataque number(25),\n"
                + "    defensa number(25),\n"
                + "    faccion_id integer,\n"
                + "    FOREIGN KEY(faccion_id) REFERENCES Faccion(faccion_id)\n"
                + ");";
		createNewTable(sql);
		
		
	}

	/**
	 * Connect to a sample database
	 */
	public static void connect() {
		Connection conn = null;
		try {
			// db parameters
			// url = "jdbc:sqlite:C";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	 public static void createNewDatabase(String fileName) {
		 
	        //url = "jdbc:sqlite:C" + fileName;
	 url=url+fileName;
	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }
	 
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 public static void createNewTable(String sql) {
	        // SQLite connection string
	       // url = "jdbc:sqlite:C://forHonor.db";
	        try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

}