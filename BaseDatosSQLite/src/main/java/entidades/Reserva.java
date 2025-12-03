package entidades;

import java.time.LocalDate;

public class Reserva {

	private int idcliente;
	private String nombreCliente;
	private LocalDate fecha;
	private int habitacion;
	private String hotel;

	public Reserva(int idcliente, LocalDate fecha, int habitacion, String hotel, String nombreCliente) {

		this.idcliente = idcliente;
		this.fecha = fecha;
		this.habitacion = habitacion;
		this.hotel = hotel;
		this.nombreCliente = nombreCliente;
	}

	public Reserva(int idcliente, LocalDate fecha, String hotel,String nombreCliente) {

		this.idcliente = idcliente;
		this.nombreCliente = nombreCliente;
		this.fecha = fecha;
		this.hotel = hotel;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getCliente() {
		return idcliente;
	}

	public void setCliente(int cliente) {
		this.idcliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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
