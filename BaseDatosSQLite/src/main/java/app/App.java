package app;

import java.sql.SQLException;

import casos_uso.CasoDos;
import casos_uso.CasoUno;
import entidades.Interfaz_de_Pasarela_Clientes;
import pasarela_datos.GestorBD;
import pasarela_datos.Pasarela_sqlite_Clientes;
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
			Interfaz_de_Pasarela_Clientes pasarela = new Pasarela_sqlite_Clientes();

			// -----CASOS DE USO-----

			// Crear el caso (inyectando la pasarela)
			CasoUno casoUno = new CasoUno(pasarela);
			CasoDos casoDos = new CasoDos(pasarela);

			// -----PRESENTACION-----

			// Crear el presentador (inyectando casos)
			// Puerto para la vista
			new Presentador(casoUno, casoDos);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
