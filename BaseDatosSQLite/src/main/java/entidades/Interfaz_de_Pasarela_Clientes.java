package entidades;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Interfaz_de_Pasarela_Clientes {
	void guardar(Cliente cliente) throws SQLException;
    List<Cliente> buscarPorId(int id) throws SQLException;
    List<Cliente> obtenerTodos() throws SQLException;
    int eliminarPersona(int id)throws SQLException;
    public void actualizarPersona(Cliente persona) throws SQLException;
}
