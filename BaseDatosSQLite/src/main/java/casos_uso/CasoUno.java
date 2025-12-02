package casos_uso;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import entidades.Cliente;
import entidades.Interfaz_de_Pasarela_Clientes;

public class CasoUno {

	private Interfaz_de_Pasarela_Clientes pasarela = null;
	
	
	public CasoUno(Interfaz_de_Pasarela_Clientes pasarela) {
		this.pasarela = pasarela;
	}

	public List<Cliente> listarTodasLasPersonas() throws SQLException {
		
		return pasarela.obtenerTodos();
	}

	public void introducirCliente(String[] valores) throws SQLException {
		Cliente c = new Cliente(valores[0], valores[1], valores[2]);
		pasarela.guardar(c);
		
	}

	public List<Cliente> buscarPersona(int id) throws SQLException {
		return pasarela.buscarPorId(id);
		
	}
	
	public void actualizarPersona(Cliente cliente)throws SQLException {
		this.pasarela.actualizarPersona(cliente);
	}
	public int eliminarPersona(int id)throws SQLException{
		return this.pasarela.eliminarPersona(id);
		
	}

}
