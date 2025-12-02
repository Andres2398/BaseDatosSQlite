package entidades;

public class Reserva {
	private int cliente;
	private String fecha;
	private int habitacion;
	private String hotel;
	
	
	
	
	
	
	
	
	public Reserva(int cliente, String fecha, int habitacion, String hotel) {
	
		this.cliente = cliente;
		this.fecha = fecha;
		this.habitacion = habitacion;
		this.hotel = hotel;
	}
	
	
	
	
	
	
	
	public Reserva(int cliente, String fecha, String hotel) {
		
		this.cliente = cliente;
		this.fecha = fecha;
		this.hotel = hotel;
	}







	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(int habitacion) {
		this.habitacion = habitacion;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	
	
	
	
}
