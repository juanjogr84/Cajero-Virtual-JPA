package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import eventos.modelo.javabeans.Evento;
import eventos.modelo.javabeans.Tipo;

@Repository
public class EventoDaoImplList implements EventoDao {
	
	public List<Evento> lista;
	private static int idAuto;
	
	static {
		idAuto = 0;
	}
	
	public EventoDaoImplList() {
		lista = new ArrayList<>();
		cargarLista();
	}

	//Método usado para crear una lista de eventos ya que no hay acceso a base de datos.
	private void cargarLista() {
		TipoDaoImplList tipoDao = new TipoDaoImplList();
		
		lista.add(new Evento(1, "Boda Castillo-Orejuela", "Celebración en salón El Coral", new Date() ,300, "C/Dulce,8", "Activo","S", 250, 70, 5980.00, tipoDao.findById(1) ));
		lista.add(new Evento(2, "Cumpleaños Miguel Gutierrez", "Cumpleaños infantil", new Date(), 100, "C/Dana,80", "Activo", "S", 40, 15, 700.50, tipoDao.findById(2)));
		lista.add(new Evento(3, "Despedida Marta Alamo", "Despedida en terraza/piscina", new Date(), 80, "C/Pez,30", "Activo", null, 45, 10, 900.00, tipoDao.findById(3)));
		lista.add(new Evento(4, "Concierto Luis Fonsi", "Concierto al aire libre", new Date(), 90, "C/Pascal,25", "Activo", "S", 2000, 500, 150000.00, tipoDao.findById(4)));
		idAuto = 5;
	}


	/**
	 * Método que devuelve un objeto de clase Evento según el idEvento que le pasemos.
	 * @return objeto de clase Evento
	 * @return null (si no encuentra el idEvento)
	 */
	@Override
	public Evento findById(int idEvento) {
		for (int i = 0; i < lista.size();i++) {
			if (lista.get(i).getIdEvento() == idEvento)
				return lista.get(i);
		}
		return null;
	}

	
	/**
	 * Método que devuelve la lista completa de eventos.
	 * @return lista
	 */
	@Override
	public List<Evento> findAll() {
		return lista;
	}

	
	/**
	 * Método que añade un nuevo evento si la lista no lo contiene. 
	 * @return 1 (se añade el evento ya que la lista no lo contiene)
	 * @return 0 (no se añade el evento ya que este existe en la lista)
	 */
	@Override
	public int insert(Evento evento) {
		if (!lista.contains(evento)) {
			evento.setIdEvento(idAuto++);
			lista.add(evento);
			return 1;
		}
		return 0;
	}

	
	/**
	 * Método que elimina de la lista el objeto evento que le pasemos por el idEvento.
	 * @return 0 (si el evento no existe, no lo elimina)
	 * @return lista.remove(evento) ? 1 : 0 (si la lista contiene el evento, lo elimina)
	 */
	@Override
	public int delete(int idEvento) {
		Evento evento = findById(idEvento);
		if (evento == null) 
			return 0;
		
		return lista.remove(evento) ? 1 : 0;
	}

	
	/**
	 * Método que se utiliza para modificar datos de un objeto tipo Evento.
	 * Busca en el array la posición del evento cuyos datos coinciden con el evento pasado como argumento
	 * y actualiza en la lista los campos que hayan sido modificados.
	 */
	@Override
	public int updateOne(Evento evento) {
		int pos = lista.indexOf(evento);
		if (pos == -1)
			return 0;
		lista.set(pos, evento);
		return 1;
	}
}
