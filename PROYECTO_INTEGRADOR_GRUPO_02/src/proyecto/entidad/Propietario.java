package proyecto.entidad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_propietario")
@Getter
@Setter
public class Propietario {

	private int cod_propietario;
	private String nom_propietario;
	private String dni_propietario;
	private int edad_propietario;
	private String correo_propietario;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha_incio_contrato;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha_fin_contrato;
	private int cod_usuario;
	
	
}
