package eventos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.javabeans.Evento;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private EventoDao edao;
	
	@Autowired
	private TipoDao tdao;

	
	//ALTA
	/**
	 * El metodo mostrarAlta muestra el formulario de alta de un nuevo evento (Alta.html)
	 * @return alta (el formulario de alta)
	 */
	@GetMapping("/alta")
	public String mostrarAlta() {
		return "alta";
	}
	
	
	/**
	 * El método procAlta procesa el alta de un nuevo evento insertado en el formulario de alta.
	 * Por defecto se pone el evento con estado activo.
	 * Se le pasa al evento el idTipo, para que al crear el nuevo evento, a este se le pase el idTipo,
	 * y este le pase todo los datos del objeto tipo al nuevo evento creado.
	 * Se crea un nuevo evento y al darlo de alta nos manda de vuelta a la página principal
	 * donde el nuevo evento se mostrará junto al resto de eventos activos.
	 * @param evento (el objeto evento)
	 * @return redirect:/ (la página principal, Index.html)
	 */
	@PostMapping("/alta")
	public String procAlta(Evento evento) {
		evento.setEstado("Activo");
		evento.setTipo(tdao.findById(evento.getTipo().getIdTipo()));
		if ((edao.insert(evento) == 1)) { 
			System.out.println(evento);
		}return "redirect:/";
	}
	
	
	//ELIMINAR
	/**
	 * El método eliminarEvento coge el idEvento del evento que queremos borrar y elimina el objeto.
	 * Al eliminarlo, desaparece de la lista de eventos del index.html, ya que este evento ya no existe.
	 * @param idEvento
	 * @return redirect:/ (la página principal, Index.html)
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") int idEvento) {
		if (edao.delete(idEvento) == 1) {
		}
		return "redirect:/";	
	}
	
	
	//VER DETALLES
	/**
	 * Al método verDetalle se le pasa el idEvento y nos muestra todos los campos con la información
	 * completa del evento que hayamos seleccionado. Los datos se muestran en verDetalle.html
	 * @param idEvento
	 * @param model
	 * @return verDetalle (la página verDetalle.html, que contiene los datos completos del evento)
	 */
	@GetMapping("/verDetalle/{id}")
	public String verEvento(@PathVariable("id") int idEvento , Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "verDetalle";
		}
		else {
			return "forward:/";
		}	
	}
	
	
	//CANCELAR
	/**
	 * Al método cancelarEvento se le pasa el idEvento del evento que queremos cancelar.
	 * El estado del evento pasa de activo a cancelado. Este método no elimina el evento, 
	 * simplemente lo modifica y lo oculta de la lista de eventos activos.
	 * Los eventos cancelados se muestran por consola.
	 * @param idEvento
	 * @return redirect:/ (la página principal, Index.html)
	 */
	@GetMapping("/cancelar/{id}")
	public String cancelarEvento(@PathVariable("id") int idEvento) {
		Evento evento = edao.findById(idEvento);
		evento.setEstado("Cancelado");
		edao.updateOne(evento);
		return "redirect:/";
	}
	
	
	//EDITAR
	/**
	 * El método editarEvento muestra el formulario de edición de un evento ya existente (editar.html)
	 * @param idEvento
	 * @param model
	 * @return editar (el formulario de edicion del evento, editar.html)
	 */
	@GetMapping("/editar/{id}")
	public String editarEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "editar";
		}
		else {
			return "forward:/";
		}
		
	}
	
	/**
	 * El método procEditarEvento actualiza el evento existente con las modificaciones de los campos que hemos editado.
	 * @param evento (el objeto evento)
	 * @param idEvento
	 * @return redirect:/ (la página principal, Index.html)
	 */
	@PostMapping("/editar/{id}")
	public String procEditarEvento(@ModelAttribute("evento") Evento evento, @PathVariable("id") int idEvento) {
	    Evento eventoExistente = edao.findById(idEvento);

	    if (eventoExistente != null) {
	    	eventoExistente.setNombre(evento.getNombre());
	    	eventoExistente.setDescripcion(evento.getDescripcion());
	    	eventoExistente.setFechaInicio(evento.getFechaInicio());
	    	eventoExistente.setDuracion(evento.getDuracion());
	    	eventoExistente.setDireccion(evento.getDireccion());
	    	eventoExistente.setDestacado(evento.getDestacado());
	    	eventoExistente.setAforoMaximo(evento.getAforoMaximo());
	    	eventoExistente.setMinimoAsistencia(evento.getMinimoAsistencia());
	    	eventoExistente.setPrecio(evento.getPrecio());
	    	eventoExistente.setTipo(tdao.findById(evento.getTipo().getIdTipo()));

	        edao.updateOne(eventoExistente); 
	    } 
	        return "redirect:/";
	 }
	
		

	//Formato fechas
	/**
	 * Método para poner el formato que deseemos a la fecha.
	 * @param binder
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
}
