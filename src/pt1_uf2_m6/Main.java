package pt1_uf2_m6;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
	static ArrayList<Faccion> facciones;
	static ArrayList<Personaje> personajes;
	// la ruta(url) debe existir
	static String sql, url2 = "jdbc:sqlite:C:\\sqlite\\java";
	static String url = "jdbc:sqlite:";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		facciones = new ArrayList<>();
		personajes = new ArrayList<>();
		Faccion caballeros = new Faccion(1, "Caballeros",
				"Los caballeros de Ashfeld son paradigmas del poder. La Legi�n de Hierro los envi� para llevar la paz a esas tierras y, desde entonces, disfrutan de la libertad y han hecho de Ashfeld su hogar. Creen que muchas (si no todas) de las antiguas ruinas que cubren sus tierras fueron construidas por el Gran Imperio, precursor de la Legion de Hierro.");
		Faccion vikingos = new Faccion(2, "Vikingos",
				"Los vikingos desaparecieron hace siglos, tras escapar de sus destrozadas tierras natales en pos de costas desconocidas. Los caballeros conquistaron a aquellos que se quedaron atras e hicieron que se incorporaran a su cultura. ");
		Faccion samur�is = new Faccion(3, "Samur�is",
				"La historia no ha tratado bien a los samurais. Originarios de una tienda allende los mares, cuentan la historia de un emperador y una patria que desaparecieron entre el mar y el fuego. Casi un milenio despu�s, la naci�n nomada ha dejado de errar y ha construido un nuevo imperio cerca de las tierras reclamadas por los vikingos y por las que disputan los caballeros.");
		facciones.add(caballeros);
		facciones.add(vikingos);
		facciones.add(samur�is);
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
		// descomenta el codigo para crear la base de datos, las tablas la inserccion de
		// datos
		/*
		 * sql="CREATE TABLE IF NOT EXISTS Faccion (\n" +
		 * "    faccion_id integer PRIMARY KEY,\n" +
		 * "    nombre_faccion VARCHAR(15) NOT NULL,\n" + "    lore  VARCHAR(200)\n" +
		 * ");"; createNewTable(sql); sql="CREATE TABLE IF NOT EXISTS Personaje (\n" +
		 * "    personaje_id integer PRIMARY KEY,\n" +
		 * "    nombre_personaje VARCHAR(15) NOT NULL,\n" + "    ataque number(25),\n" +
		 * "    defensa number(25),\n" + "    faccion_id integer,\n" +
		 * "    FOREIGN KEY(faccion_id) REFERENCES Faccion(faccion_id)\n" + ");";
		 * createNewTable(sql); for(int i=0;i<facciones.size();i++){
		 * facciones.get(1).insertFaccion(facciones.get(i), url); }
		 * 
		 * for(int j=0;j<personajes.size();j++) {
		 * personajes.get(1).insertPersonaje(personajes.get(j), url); }
		 */
		busquedaTodosPersonajes();
		busquedaCaballeros();
		busquedaSamuraiMasPoderoso();
	}

	public static void busquedaTodosPersonajes() {
		String sql2 = "SELECT * FROM Personaje;";
		// sql2 = "select * from Personaje";

		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2)) {

			// loop through the result set
			System.out.println("Busqueda de todos los personajes");
			System.out.println("personaje_id|nombre_personaje|ataque|defensa|faccion_id");
			while (rs.next()) {
				System.out.println(rs.getInt("personaje_id") + "\t" + rs.getString("nombre_personaje") + "\t"
						+ rs.getFloat("ataque") + "\t" + rs.getFloat("defensa") + "\t" + rs.getInt("faccion_id"));
			}
			System.out.println("____________________________________________________");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void busquedaCaballeros() {
		String sql2 = "SELECT * FROM Personaje where faccion_id='1';";
		// sql2 = "select * from Personaje";

		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2)) {

			// loop through the result set
			System.out.println("Busqueda de todos los caballeros");
			System.out.println("personaje_id|nombre_personaje|ataque|defensa|faccion_id");
			while (rs.next()) {
				System.out.println(rs.getInt("personaje_id") + "\t" + rs.getString("nombre_personaje") + "\t"
						+ rs.getFloat("ataque") + "\t" + rs.getFloat("defensa") + "\t" + rs.getInt("faccion_id"));

			}
			System.out.println("____________________________________________________");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void busquedaSamuraiMasPoderoso() {
		String sql3 = "select * from Personaje where faccion_id='3' and ataque=(select max(ataque) from Personaje where faccion_id='3');";

		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs1 = stmt.executeQuery(sql3)) {

			// loop through the result set

			System.out.println("Busqueda del samurai con mas ataque");
			System.out.println("personaje_id|nombre_personaje|ataque|defensa|faccion_id");
			System.out.println(rs1.getInt("personaje_id") + "\t" + rs1.getString("nombre_personaje") + "\t"
					+ rs1.getFloat("ataque") + "\t" + rs1.getFloat("defensa") + "\t" + rs1.getInt("faccion_id"));
			System.out.println("____________________________________________________");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

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

		url = url + fileName;
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
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("table has been created");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}