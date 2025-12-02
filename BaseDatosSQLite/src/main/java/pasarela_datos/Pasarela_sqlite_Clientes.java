package pasarela_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import entidades.Cliente;
import entidades.Interfaz_de_Pasarela_Clientes;

public class Pasarela_sqlite_Clientes implements Interfaz_de_Pasarela_Clientes {

	@Override
	public void guardar(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO clientes(nombre, email, telefono) VALUES(?,?,?)";

		try (Connection conn = GestorBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cliente.getNombre());
			pstmt.setString(2, cliente.getEmail());
			pstmt.setString(3, cliente.getTelefono());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error al guardar, " + e.getMessage());
		}

	}

	@Override
	public List<Cliente> buscarPorId(int id) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE id = ?";
		List <Cliente> l = new LinkedList<Cliente>();
		try (Connection conn = GestorBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Cliente c = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"),
						rs.getString("telefono"));
				l.add(c);
				

			}
			return l;

		} catch (SQLException e) {
			throw new SQLException("Error al buscar por id, " + e.getMessage());
		}

	}

	@Override
	public List<Cliente> obtenerTodos() throws SQLException {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM clientes";

		try (Connection conn = GestorBD.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Cliente p = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"),rs.getString("telefono"));
				clientes.add(p);
				

			}

		} catch (SQLException e) {
			throw new SQLException("Error al listar todos, " + e.getMessage());
		}

		return clientes;
	}
	

	@Override
	public int eliminarPersona(int id) throws SQLException {
		String sql = "DELETE FROM clientes WHERE id = ?";

		try (Connection conn = GestorBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, id);
			return ps.executeUpdate();
						
		} catch (SQLException e) {
			throw new SQLException("Error al eliminar por id, " + e.getMessage());
		}
	}

	@Override
	public void actualizarPersona(Cliente cliente) throws SQLException {
		String sql = "UPDATE clientes SET nombre = ?, email = ?, telefono = ? WHERE id = ?";

		try (Connection conn = GestorBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			
			
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getEmail());
			ps.setString   (3, cliente.getTelefono());
			ps.setInt(4, cliente.getId());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			throw new SQLException("Error al actuallizar, " + e.getMessage());
		}
	}

	}


