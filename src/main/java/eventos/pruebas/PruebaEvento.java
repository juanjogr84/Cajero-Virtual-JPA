package eventos.pruebas;

import eventos.modelo.dao.EventoDaoImplList;

public class PruebaEvento {

	public static void main(String[] args) {
		EventoDaoImplList eDao = new EventoDaoImplList();
		System.out.println(eDao.findAll());

	}

}
