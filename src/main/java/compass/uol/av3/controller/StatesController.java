package compass.uol.av3.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import compass.uol.av3.dto.StatesDto;
import compass.uol.av3.modelo.States;
import compass.uol.av3.services.StatesService;
import compass.uol.av3.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping("api/states")
public class StatesController {

	@Autowired
	private StatesService statesService;

	@GetMapping
	public ResponseEntity<List<States>> findAll() {
		return ResponseEntity.ok(statesService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<States> findById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(statesService.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<States> insert(@RequestBody States states) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(states.getId()).toUri();
		return ResponseEntity.created(uri).body(statesService.insert(states));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<States> delete(@PathVariable(value = "id") Long id) {
		statesService.delete(id);
		return ResponseEntity.noContent().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<States> update(@PathVariable(value = "id") Long id, @RequestBody States states) {
		if (states.getRegiao().equalsIgnoreCase("Norte") || states.getRegiao().equalsIgnoreCase("Nordeste")
				|| states.getRegiao().equalsIgnoreCase("Sul") || states.getRegiao().equalsIgnoreCase("Centro-Oeste")
				|| states.getRegiao().equalsIgnoreCase("Sudeste")) {
		} else {
			throw new ObjectNotFoundException("Região Alterada não existe");
		}
		statesService.update(id, states);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/regiao")
	public ResponseEntity<List<StatesDto>> filtrarRegiao(@RequestParam String regiao) {
		return ResponseEntity.ok(statesService.filtrarRegiao(regiao));
	}
	
	@GetMapping("/populacao")
	public ResponseEntity<List<StatesDto>> filtrarMaioresPopulacoes(@RequestParam Long populacao) {
		return ResponseEntity.ok(statesService.filtrarPopulacao(populacao));
	}
	
	@GetMapping("/area")
	public ResponseEntity<List<StatesDto>> procurarMaioresAreas(@RequestParam Double area) {
		return ResponseEntity.ok(statesService.filtrarArea(area));
	}
}
