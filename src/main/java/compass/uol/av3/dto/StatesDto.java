package compass.uol.av3.dto;

import java.util.List;
import java.util.stream.Collectors;

import compass.uol.av3.modelo.States;

public class StatesDto {

	private Long id;
	private String nome;
	private String regiao;
	private Long populacao;
	private String capital;
	private double area;
	
	public StatesDto() {
		
	}

	public StatesDto(States states) {
		this.id = states.getId();
		this.nome = states.getNome();
		this.regiao = states.getRegiao();
		this.populacao = states.getPopulacao();
		this.capital = states.getCapital();
		this.area = states.getArea();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public double getArea() {
		return area;
	}

	public static List<StatesDto> converter(List<States> states) {
		return states.stream().map(StatesDto::new).collect(Collectors.toList());
	}

}
