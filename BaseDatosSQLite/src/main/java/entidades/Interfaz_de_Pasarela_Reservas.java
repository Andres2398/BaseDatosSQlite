package entidades;

import java.sql.SQLException;

public interface Interfaz_de_Pasarela_Reservas {
	public boolean reservar(Reserva r) throws SQLException;
	public boolean actualizarReserva(Reserva r)throws SQLException; 
	public void eliminarReserva(Reserva r)throws SQLException;
	public void buscarReservaCliente (int cliente)throws SQLException;
	
	

}
