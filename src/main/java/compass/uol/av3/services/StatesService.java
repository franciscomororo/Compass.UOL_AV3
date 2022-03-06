package compass.uol.av3.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import compass.uol.av3.dto.StatesDto;
import compass.uol.av3.modelo.States;
import compass.uol.av3.repository.StatesRepository;
import compass.uol.av3.services.exception.ObjectNotFoundException;
import compass.uol.av3.services.exception.TempFoundationException;

@Service
public class StatesService {

	@Autowired
	private StatesRepository statesRepository;

	public List<States> findAll() {
		return statesRepository.findAll();
	}

	public States findById(Long id) {
		Optional<States> states = statesRepository.findById(id);
		if (states.isEmpty()) {
			throw new ObjectNotFoundException("Id não encontrado");
		} else {
			return states.get();
		}

	}

	public States insert(States states) {
		comparaTempoFundacao(states);
		if (states.getRegiao().equalsIgnoreCase("Norte") || states.getRegiao().equalsIgnoreCase("Nordeste")
				|| states.getRegiao().equalsIgnoreCase("Sul") || states.getRegiao().equalsIgnoreCase("Centro-Oeste")
				|| states.getRegiao().equalsIgnoreCase("Sudeste")) {
		} else {
			throw new ObjectNotFoundException("Região Cadastrada não existe");
		}
		return statesRepository.save(states);
	}

	public void delete(Long id) {
		Optional<States> states = statesRepository.findById(id);
		if (states.isEmpty()) {
			throw new ObjectNotFoundException("Id não encontrado");
		}
		statesRepository.deleteById(id);
	}

	public States update(Long id, States states) {
		comparaTempoFundacao(states);
		States newStates = statesRepository.findById(id).orElse(null);
		updateStates(newStates, states);
		return statesRepository.save(newStates);
	}

	private void updateStates(States newStates, States states) {
		newStates.setNome(states.getNome());
		newStates.setRegiao(states.getRegiao());
		newStates.setPopulacao(states.getPopulacao());
		newStates.setCapital(states.getCapital());
		newStates.setArea(states.getArea());
		newStates.setDataDeFundacao(states.getDataDeFundacao());
		newStates.setTempoDesdeFundacao(states.getTempoDesdeFundacao());

	}

	private void comparaTempoFundacao(States states) {
		LocalDate now = LocalDate.now();
		Integer anos = Period.between(states.getDataDeFundacao(), now).getYears();
		if (!anos.equals(states.getTempoDesdeFundacao())) {
			throw new TempFoundationException("Tempo ou data de fundação invalido");
		}
	}

	public List<StatesDto> filtrarRegiao(@RequestParam String regiao) {
		List<StatesDto> listarRegiao = statesRepository.findAll().stream()
				.filter(states -> states.getRegiao().equalsIgnoreCase(regiao)).map(StatesDto::new)
				.collect(Collectors.toList());

		return listarRegiao;
	}

	public List<StatesDto> filtrarPopulacao(Long populacao) {
		List<StatesDto> listarMaioresPopulacoes = statesRepository.findAll().stream()
				.filter(states -> states.getPopulacao() >= populacao)
				.sorted(Comparator.comparing(States::getPopulacao).reversed()).map(StatesDto::new)
				.collect(Collectors.toList());
		return listarMaioresPopulacoes;
	}

	public List<StatesDto> filtrarArea(Double area) {
		List<StatesDto> listarMaioresAreas = statesRepository.findAll().stream()
				.filter(states -> states.getArea() >= area).sorted(Comparator.comparing(States::getArea).reversed())
				.map(StatesDto::new).collect(Collectors.toList());
		return listarMaioresAreas;
	}

}
