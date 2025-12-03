package casos_uso;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entidades.Cliente;
import entidades.Interfaz_de_Pasarela_Clientes;
import entidades.Interfaz_de_Pasarela_Reservas;
import entidades.Reserva;

public class CasoDos {

	private Interfaz_de_Pasarela_Reservas pasarela = null;
	
	public CasoDos(Interfaz_de_Pasarela_Reservas pasarela) {
		this.pasarela=pasarela;
	}

	public boolean reservar(Reserva r) throws SQLException {
		return pasarela.reservar(r);
	}
	
	public List<Reserva> todasReservas(int idCliente) throws SQLException{
		List<Reserva> lista = new LinkedList<Reserva>();
		pasarela.buscarReservaCliente(idCliente, lista);
		return lista;
	}

}
