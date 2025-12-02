package pasarela_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Interfaz_de_Pasarela_Reservas;
import entidades.Reserva;

public class Pasarela_sqlite_Reservas implements Interfaz_de_Pasarela_Reservas {

	@Override
	public boolean reservar(Reserva r) throws SQLException {
		String sql = "INSERT INTO Reservas(Cliente, habitacion, fecha, Hotel) VALUES(?,?,?)";
		String sqlLibre = """
				    SELECT numero FROM habitaciones
				    WHERE Hotel = ?
				    AND numero NOT IN (
				        SELECT habitacion FROM Reservas
				        WHERE Hotel = ?
				        AND fecha = ?
				    )
				    LIMIT 1
				""";
		try (Connection con = GestorBD.getConnection()) {

			int habitacionLibre = -1;

			try (PreparedStatement ps = con.prepareStatement(sqlLibre)) {
				ps.setString(1, r.getHotel());
				ps.setString(2, r.getHotel());
				ps.setString(3, r.getFecha());

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					habitacionLibre = rs.getInt("numero");
				} else {

					return false;
				}
			}

			// Insertar reserva
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, r.getCliente());
				ps.setInt(2, habitacionLibre);
				ps.setString(3, r.getFecha());
				ps.setString(4, r.getHotel());

				ps.executeUpdate();
			}

			return true;
		}

	}

	@Override
	public boolean actualizarReserva(Reserva r) {

		return false;
	}

	@Override
	public void eliminarReserva(Reserva r) {

	}

	@Override
	public void buscarReservaCliente(int cliente) {

	}

}
