package pasarela_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entidades.Interfaz_de_Pasarela_Reservas;
import entidades.Reserva;

public class Pasarela_sqlite_Reservas implements Interfaz_de_Pasarela_Reservas {

	@Override
	public boolean reservar(Reserva r) throws SQLException {
		String sql = "INSERT INTO Reservas(iDCliente, nombreCliente ,habitacion, fecha, Hotel) VALUES(?,?,?,?,?)";
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
				ps.setString(3, r.getFecha().toString());

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					habitacionLibre = rs.getInt("numero");
				} else {

					return false;
				}
			}

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, r.getCliente());
				ps.setString(2, r.getNombreCliente());
				ps.setInt(3, habitacionLibre);
				ps.setString(4, r.getFecha().toString());
				ps.setString(5, r.getHotel());

				ps.executeUpdate();
			}

			return true;
		}

	}

	@Override
	public void buscarReservaCliente(int cliente, List<Reserva> lista) throws SQLException {
		String sql = "Select * from Reservas where iDCliente = ?";
		try (Connection con = GestorBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cliente);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(new Reserva(rs.getInt("iDCliente"), LocalDate.parse(rs.getString("fecha")),
						rs.getInt("habitacion"), rs.getString("Hotel"),rs.getString("nombreCliente")));
			}

		}
	}

	@Override
	public boolean actualizarReserva(Reserva r) throws SQLException {

		return false;
	}

	@Override
	public void eliminarReserva(Reserva r) throws SQLException {

	}

}
