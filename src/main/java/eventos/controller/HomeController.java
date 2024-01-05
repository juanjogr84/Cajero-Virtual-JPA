package eventos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.javabeans.Evento;

@Controller
public class HomeController {
	
	@Autowired
	private EventoDao edao;
	
	/**
	 * El método mostrarHome muestra por pantalla todos los eventos con estado activo.
	 * Para ello, nos recorremos todos los eventos de la lista con un for y los filtramos para que solo coja
	 * los que su estado sea activo. Una vez recogidos los datos, estos se muestran a traves del index.html
	 * en las tablas que se han creado en el documento.
	 * @param model
	 * @return index (index.html)
	 */
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Evento> evActivo = new ArrayList<>();
		
		for(Evento evento: edao.findAll()) {
			if(evento.getEstado().equalsIgnoreCase("activo"))
				evActivo.add(evento);
			else
				System.out.println(evento);
				System.out.println("  ");
		}
		model.addAttribute("eventosActivos", evActivo);
		return "index";
	}
	

}
