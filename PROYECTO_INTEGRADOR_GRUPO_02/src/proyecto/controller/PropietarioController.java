package proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import proyecto.entidad.Propietario;
import proyecto.service.PropietarioService;

@RestController
@RequestMapping("/rest/propietario")
public class PropietarioController {

	@Autowired
	private PropietarioService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Propietario>> listaPropietario() {
		List<Propietario> lista = service.listaPropietario();
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/dni/{dni_propietario}")
	@ResponseBody
	public ResponseEntity<List<Propietario>> listaPropietarioPorDni(@PathVariable("dni_propietario")String dni_propietario) {
		List<Propietario> lista = service.listaPropietarioPorDni(dni_propietario);
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/filtro/{nom_propietario}-{correo_propietario}")
	@ResponseBody
	public ResponseEntity<List<Propietario>> listaPropietarioPorNombreOrCorreo(@PathVariable("nom_propietario") String nom_propietario, @PathVariable ("correo_propietario") String correo_propietario) {
		List<Propietario> lista = service.listaPropietarioPorNombreOrCorreo(nom_propietario,correo_propietario);
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaPropietario(@RequestBody Propietario obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Propietario> lstPropietario = service.listaPropietarioPorDni(obj.getDni_propietario());
			if (CollectionUtils.isEmpty(lstPropietario)) {
				obj.setCod_propietario(0);// Para que registre, sino actualiza
				Propietario objSalida = service.insertaActualizaPropietario(obj);
				if (objSalida == null) {
					salida.put("mensaje", "Error en el registro");
				} else {
					salida.put("mensaje", "Se registro correctamente");
				}
			} else {
				salida.put("mensaje", "El dni " + obj.getDni_propietario() + " ya existe.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro");
		}
		return ResponseEntity.ok(salida);
	}
	@PutMapping

	@ResponseBody

	public ResponseEntity<HashMap<String, Object>> actualizaPropietario(@RequestBody Propietario obj){

		HashMap<String, Object> salida = new HashMap<String, Object>();

		try {

			Optional<Propietario> optional = service.buscaPorCodigo(obj.getCod_propietario());

			if (optional.isPresent()) {

				 List<Propietario> list = service.listaPropietarioXDniDiferenteDelMismo(obj.getDni_propietario(), obj.getCod_propietario());

				 if (CollectionUtils.isEmpty(list)) {

					 Propietario objSalida = service.insertaActualizaPropietario(obj);

					 if (objSalida == null) {

						 salida.put("mensaje", "Error en la actualización");

					 }else {

						 salida.put("mensaje", "Éxito en la actualización");

					 }

				 }else {

					 salida.put("mensaje", "El Dni ya existe : " + obj.getDni_propietario());

				 }

			}else {

				salida.put("mensaje", "No existe el ID: " + obj.getCod_propietario());

			}

		} catch (Exception e) {

			e.printStackTrace();

			salida.put("mensaje", "Error en la actualización");

		}

		return ResponseEntity.ok(salida);

	}
	
	
	
	
	@DeleteMapping("/{cod_propietario}")

	@ResponseBody

	public ResponseEntity<HashMap<String, Object>> eliminaPropietario(@PathVariable("cod_propietario")int cod_propietario){

		HashMap<String, Object> salida = new HashMap<String, Object>();

		try {

			Optional<Propietario> optional = service.buscaPorCodigo(cod_propietario);

			if (optional.isEmpty()) {

				salida.put("mensaje", "No existe el ID: " + cod_propietario);

			}else {

				service.eliminaPorCodigo(cod_propietario);

				salida.put("mensaje", "Éxito en la eliminación");

			}

		} catch (Exception e) {

			e.printStackTrace();

			salida.put("mensaje", "Error en la eliminación");

		}

		return ResponseEntity.ok(salida);

	}
}

