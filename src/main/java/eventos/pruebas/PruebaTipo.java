package eventos.pruebas;

import eventos.modelo.dao.TipoDaoImplList;
import eventos.modelo.javabeans.Evento;
import eventos.modelo.javabeans.Tipo;

public class PruebaTipo {

	public static void main(String[] args) {
		Tipo tipo = new Tipo();
		TipoDaoImplList tipoDao = new TipoDaoImplList();
		System.out.println(tipoDao.findAll());

	}

}
