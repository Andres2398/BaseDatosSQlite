package presentacion.interfaz;

import javax.swing.table.DefaultTableModel;

public class Formatos {

	
	
	
	static DefaultTableModel  formatoCliente() {
		String[] nombresColumnas = {"ID", "Nombre", "Email", "Teléfono"};
		DefaultTableModel df = new DefaultTableModel(null,nombresColumnas);
		return df;
	}
	
	static DefaultTableModel  formatoReserva() {
		String[] nombresColumnas = {"IDCliente","nombreCliente", "Fecha", "Habitacion", "Hotel"};
		DefaultTableModel df = new DefaultTableModel(null,nombresColumnas);
		return df;
	}
}
