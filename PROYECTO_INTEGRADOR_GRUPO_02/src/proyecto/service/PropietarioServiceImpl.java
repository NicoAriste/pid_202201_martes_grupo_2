package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import proyecto.entidad.Propietario;
import proyecto.repository.PropietarioRepository;

public class PropietarioServiceImpl implements PropietarioService{

	@Autowired
	private PropietarioRepository repository;

	
	@Override
	public List<Propietario> listaPropietario() {
		return repository.findAll();
	
	}

	@Override
	public Propietario insertaActualizaPropietario(Propietario obj) {
		return repository.save(obj);
	}

	@Override
	public List<Propietario> listaPropietarioPorDni(String dni_propietario) {
		return repository.findByDni(dni_propietario);
	}

	@Override
	public List<Propietario> listaPropietarioPorNombreOrCorreo(String nom_propietario, String correo_propietario) {
		return repository.listaPropietarioPorNombreOrCorreo(nom_propietario, correo_propietario);
	}

	@Override
	public Optional<Propietario> buscaPorCodigo(int cod_propietario) {
		return repository.findById(cod_propietario);
	}

	@Override
	public List<Propietario> listaPropietarioXDniDiferenteDelMismo(String dni_propietario, int cod_propietario) {
		return repository.listaPropietarioXDniDiferenteDelMismo(dni_propietario, cod_propietario);
	}

	@Override
	public void eliminaPorCodigo(int cod_propietario) {
		repository.deleteById(cod_propietario);
		
	}

}
