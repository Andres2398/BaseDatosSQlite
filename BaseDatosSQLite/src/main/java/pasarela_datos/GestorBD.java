package pasarela_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBD {

	private static final String URL = "jdbc:sqlite:src/main/resources/database.db";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL);
	}

	public static void inicializarBaseDeDatos() throws SQLException {

		final var sql1 = """
				CREATE TABLE IF NOT EXISTS clientes (
				    id INTEGER PRIMARY KEY AUTOINCREMENT,
				    nombre TEXT NOT NULL,
				    email TEXT NOT NULL UNIQUE,
				    telefono Text
				);
				""";

		final var sql2 = """
				CREATE TABLE IF NOT EXISTS Hoteles (
				    Nombre TEXT PRIMARY KEY,
				    direccion TEXT,
				    Telefono CHAR(9)
				);
				""";

		final var sql3 = """
				CREATE TABLE IF NOT EXISTS habitaciones (
				    numero INTEGER,
				    Hotel TEXT NOT NULL,
				    FOREIGN KEY (Hotel) REFERENCES Hoteles(Nombre),
				    PRIMARY KEY (Hotel, numero)
				);
				""";

		final var sql4 = """
				 CREATE TABLE IF NOT EXISTS Reservas (
				    iDCliente INTEGER NOT NULL,
				    nombreCliente TEXT NOT NULL,
				    habitacion INTEGER NOT NULL,
				    fecha TEXT NOT NULL,
				    Hotel TEXT NOT NULL,
				    PRIMARY KEY (Hotel, fecha, habitacion),
				    FOREIGN KEY (iDCliente) REFERENCES usuarios(id),
				    FOREIGN KEY (habitacion) REFERENCES habitaciones(numero),
				    FOREIGN KEY (Hotel) REFERENCES Hoteles(Nombre)
				);
				""";

		try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
			
			
			stmt.execute(sql1);
			stmt.execute(sql2);
			stmt.execute(sql3);
			stmt.execute(sql4);
			
			
		} catch (SQLException e) {
			throw new SQLException("Error al inicializar la base de datos:  " + e.getMessage());
		}
	}

	public static void introducirDatosPermanentes() throws SQLException {
		String sql1 = "Select * FROM Hoteles";
		String sql2 = "Select * FROM habitaciones";
	
		try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
		
			ResultSet rs = stmt.executeQuery(sql1);
			if (!rs.next())
				insetarHoteles();
			
			rs = stmt.executeQuery(sql2);
			
			if(!rs.next())
				insetarHabitaciones();
			
			
			
			
		}
		
		

	}

	private static void insetarHabitaciones() throws SQLException {
		String sql = "INSERT INTO habitaciones(numero, Hotel) VALUES(?,?)";
		String[] hoteles = { "Hotel Ritz Madrid", "Hotel Arts Barcelona", "Gran Meliá Sevilla", "Hotel Alfonso XIII",
				"Hotel Bahía Santander", "Parador de Granada", "NH Collection Valencia Colón", "Barceló Málaga",
				"Hotel María Cristina San Sebastián", "Eurostars Palacio Buenavista Toledo" };
		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			for (int i = 0; i < hoteles.length; i++) {
				for (int j = 1; j <= 10; j++) {
					for (int k = 1; k <= 10; k++) {
						pstmt.setInt(1, (j*100) +k);
						pstmt.setString(2, hoteles[i]);
						pstmt.executeUpdate();
						
					}
					
				}
				
			}
		}
		
	}

	private static void insetarHoteles() throws SQLException {
		String[] hoteles = { "Hotel Ritz Madrid", "Hotel Arts Barcelona", "Gran Meliá Sevilla", "Hotel Alfonso XIII",
				"Hotel Bahía Santander", "Parador de Granada", "NH Collection Valencia Colón", "Barceló Málaga",
				"Hotel María Cristina San Sebastián", "Eurostars Palacio Buenavista Toledo" };

		String[] direcciones = { "Plaza de la Lealtad 5, Madrid", "Carrer de la Marina 19–21, Barcelona",
				"Calle Dr. Pedro de Castro 1, Sevilla", "San Fernando 2, Sevilla", "Calle Cádiz 22, Santander",
				"Real de la Alhambra s/n, Granada", "Carrer de Colón 32, Valencia",
				"Estación Vialia, Explanada de la Estación s/n, Málaga", "Paseo República Argentina 4, San Sebastián",
				"Calle Concilios de Toledo s/n, Toledo" };

		String[] telefonos = { "910 555 111", "931 444 222", "955 333 444", "955 777 888", "942 222 555", "958 111 999",
				"963 888 444", "952 666 333", "943 555 777", "925 999 111" };

		String sql = "INSERT INTO Hoteles(Nombre, direccion, Telefono) VALUES(?,?,?)";

		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			for (int i = 0; i < telefonos.length; i++) {
				pstmt.setString(1, hoteles[i]);
				pstmt.setString(2, direcciones[i]);
				pstmt.setString(3, telefonos[i]);
				pstmt.executeUpdate();
			}

		}
	}
}
