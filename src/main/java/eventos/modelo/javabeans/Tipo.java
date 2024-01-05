package eventos.modelo.javabeans;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

/**
 * Objeto Tipo con sus atributos privados (idTipo, nombre y descripcion)
 * Se añade el constructor sin parametros (@NoArgsConstructor) y con todos los parametros (@AllArgsConstructor),
 * también los getter (@Getter), setter (@Setter), to string (@ToString) todo ello con Lombok
 * @author Sheila Orejuela
 *
 */
public class Tipo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idTipo;
	private String nombre;
	private String descripcion;

}

