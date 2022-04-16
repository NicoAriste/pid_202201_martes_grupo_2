package proyecto.service;

import java.util.List;
import java.util.Optional;

import proyecto.entidad.Propietario;

public interface PropietarioService {

	public abstract List<Propietario> listaPropietario();
	public abstract Propietario insertaActualizaPropietario(Propietario obj);
	public abstract List<Propietario> listaPropietarioPorDni(String dni_propietario);
	public abstract List<Propietario> listaPropietarioPorNombreOrCorreo(String nom_propietario, String correo_propietario);
	public abstract Optional<Propietario>buscaPorCodigo(int cod_propietario);
	public abstract List<Propietario>listaPropietarioXDniDiferenteDelMismo(String dni_propietario, int cod_propietario);
	public abstract void eliminaPorCodigo(int cod_propietario);
}
