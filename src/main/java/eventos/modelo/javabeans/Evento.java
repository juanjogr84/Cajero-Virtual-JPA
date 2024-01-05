package eventos.modelo.javabeans;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

/**
 * Objeto Evento con sus atributos privados (idEvento, nombre, descripcion, fechaInicio, duracion,
 * direccion, estado,destacado, aforoMaximo, minimoAsistencia, precio y tipo)
 * Se añade el constructor sin parametros (@NoArgsConstructor) y con todos los parametros (@AllArgsConstructor),
 * también los getter (@Getter), setter (@Setter), to string (@ToString), EqualsAndHasCode (@EqualsAndHashCode(onlyExplicitlyIncluded = true))
 * para que unicamente coja el Equal del campo que yo quiera, en este caso el idEvento poniendole arriba del atributo (@EqualsAndHashCode.Include)
 * todo ello con Lombok
 * @author Sheila Orejuela
 *
 */
public class Evento implements Serializable{

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int idEvento;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private int duracion;
	private String direccion;
	private String estado;
	private String destacado;
	private int aforoMaximo;
	private int minimoAsistencia;
	private double precio;
	private Tipo tipo;
}
