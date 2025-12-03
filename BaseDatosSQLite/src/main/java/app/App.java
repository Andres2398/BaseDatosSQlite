package app;

import java.sql.SQLException;

import casos_uso.CasoDos;
import casos_uso.CasoUno;
import entidades.Interfaz_de_Pasarela_Clientes;
import entidades.Interfaz_de_Pasarela_Reservas;
import pasarela_datos.GestorBD;
import pasarela_datos.Pasarela_sqlite_Clientes;
import pasarela_datos.Pasarela_sqlite_Reservas;
import presentacion.Presentador;

public class App {

	public static void main(String[] args) {
		try {
			// -----PASARELA A DATOS-----

			// Inicializar la infraestructura (Base de datos)
			GestorBD.inicializarBaseDeDatos();
			GestorBD.introducirDatosPermanentes();

			// Crear la pasarela
			// Puerto para la BBDD
			Interfaz_de_Pasarela_Clientes pasarelaClientes = new Pasarela_sqlite_Clientes();
			Interfaz_de_Pasarela_Reservas pasarelaReservas = new Pasarela_sqlite_Reservas();
			
			
			// -----CASOS DE USO-----

			// Crear el caso (inyectando la pasarela)
			CasoUno casoUno = new CasoUno(pasarelaClientes);
			CasoDos casoDos = new CasoDos(pasarelaReservas);

			// -----PRESENTACION-----

			// Crear el presentador (inyectando casos)
			// Puerto para la vista
			new Presentador(casoUno, casoDos);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
