package proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import proyecto.entidad.Propietario;

public interface PropietarioRepository extends JpaRepository<Propietario, Integer> {

public abstract List<Propietario> findByDni(String dni_propietario);
	
	@Query("select e from Propietario e where e.dni_propietario= ?1 and e.cod_propietario <> ?2")
	public abstract List<Propietario> listaUsuarioXDniDiferenteDelMismo(String dni_propietario, int cod_propietario);
	
	@Query("select e from Propietario e where e.nom_propietario= ?1 or e.correo_propietario <> ?2")
	public abstract List<Propietario> listaPropietarioPorNombreOrCorreo(String nom_propietario, String correo_propietario);
}
