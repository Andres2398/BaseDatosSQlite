package entidades;

import java.sql.SQLException;
import java.util.List;



public interface Interfaz_de_Pasarela_Reservas {
	public boolean reservar(Reserva r) throws SQLException;
	public boolean actualizarReserva(Reserva r)throws SQLException; 
	public void eliminarReserva(Reserva r)throws SQLException;
	public void buscarReservaCliente (int cliente, List<Reserva> lista)throws SQLException;
	
	

}
