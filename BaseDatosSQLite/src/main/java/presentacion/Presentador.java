package presentacion;


import java.sql.SQLException;
import java.util.List;

import java.util.Scanner;

import casos_uso.CasoDos;
import casos_uso.CasoUno;
import entidades.Cliente;
import entidades.Reserva;
import presentacion.interfaz.MenuInterfaz;

import java.time.LocalDate;
public class Presentador {

	private static Scanner sc = new Scanner(System.in);

	// El presentador CONOCE los casos de uso y a la Vista.
	private final CasoUno casoUno;
	private final CasoDos casoDos;

	private final Vista vista = new Vista(sc);
	MenuInterfaz menu = new MenuInterfaz(this);

	// Inyección de dependencias
	public Presentador(CasoUno c, CasoDos c2) {
		this.casoUno = c;
		this.casoDos = c2;

		this.aTrabajar();

	}

	private void aTrabajar() {
		
		menu.setLocationRelativeTo(null);
		

	}

	// Acción: El usuario quiere crear una persona
	public void introducirCliente(String[] valoresCliente) {
		try {
			casoUno.introducirCliente(valoresCliente);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	// Acción: El usuario quiere ver una pesona
	public void solicitarMostrarPersona(int id) {
		List<Cliente> l;
		try {
			l = casoUno.buscarPersona(id);
			menu.mostrarClientes(l);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	// Acción: El usuario quiere ver a todas las personas
	public void solicitarMostrarTodos() {
		List<Cliente> l;
		try {
			l = casoUno.listarTodasLasPersonas();
			menu.mostrarClientes(l);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	// Acción: El usuario quiere eliminar una persona
	public void eleminarPersona(int id) {
		int codigo;
		try {
			codigo = casoUno.eliminarPersona(id);
			vista.mensajeEliminar(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// Acción: El usuario quiere actulaizar una persona
	public void actualizarPersona(int id) {
		
		List<Cliente> lc;
		try {
			lc = casoUno.buscarPersona(id);
			if (!lc.isEmpty()) {
				Cliente c = lc.get(0);
				menu.actualizarPersona(c);
				casoUno.actualizarPersona(c);
			} else
				vista.mensajeERR("NO EXISTE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	

	public void buscarPersona(int idReservar) {
		List<Cliente> l;
		try {
			l = casoUno.buscarPersona(idReservar);
			if(l.get(0)!=null) {
				
			Object valoresReserva[] =menu.Reservar();
			Reserva r= new Reserva(idReservar, (LocalDate)valoresReserva[1], (String) valoresReserva[0],l.get(0).getNombre());
			casoDos.reservar(r);
			
			}
			else {
				//MEnsaje de no hay cliente
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void BuscarReservarPersona(int idbuscarReserva) {
		try {
			List <Reserva> lista= casoDos.todasReservas(idbuscarReserva);
			if(!lista.isEmpty())
				
				menu.mostrarReserva(lista);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
