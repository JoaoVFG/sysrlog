package JoaoVFG.com.github.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import JoaoVFG.com.github.entity.Cargo;
import JoaoVFG.com.github.service.CargoService;

@RestController
@RequestMapping(value = "/cargo")
public class CargoResource {

	@Autowired
	private CargoService cargoService;

	@PreAuthorize("hasRole('ROLE_BUSCA_PROPRIA_CARGO') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/buscacargo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cargo> findById(@PathVariable Integer id) {
		Cargo cargo = cargoService.findById(id);
		return ResponseEntity.ok(cargo);
	}

	@PreAuthorize("hasRole('ROLE_BUSCA_CARGO') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/buscacargo", method = RequestMethod.GET)
	public ResponseEntity<List<Cargo>> findAll() {
		List<Cargo> cargos = cargoService.findAll();
		return ResponseEntity.ok(cargos);
	}

	@PreAuthorize("hasRole('ROLE_BUSCA_CARGO') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/buscacargo/descricao/{descricao}", method = RequestMethod.GET)
	public ResponseEntity<List<Cargo>> findByDescricaoContains(@PathVariable String descricao) {
		List<Cargo> cargos = cargoService.findByDescricao(descricao);
		return ResponseEntity.ok(cargos);
	}

	@PreAuthorize("hasRole('ROLE_CREATE_CARGO') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/insert/{descricao}", method = RequestMethod.POST)
	public ResponseEntity<Void> createCargo(@PathVariable String descricao) {
		Cargo cargo = cargoService.create(descricao);
		URI uri = URI.create("/cargo" + "/buscaCargo/" + cargo.getId());
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasRole('ROLE_DELETE_CARGO') or hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCargo(@PathVariable Integer id) {
		cargoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
