package compass.uol.av3.modelo;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class States {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String regiao;
	private Long populacao;
	private String capital;
	private Double area;
	private LocalDate dataDeFundacao;
	private Integer tempoDesdeFundacao;
	

	public States() {

	}
		
	public Integer getTempoDesdeFundacao() {
		return tempoDesdeFundacao;
	}



	public void setTempoDesdeFundacao(Integer tempoDesdeFundacao) {
		this.tempoDesdeFundacao = tempoDesdeFundacao;
	}



	public LocalDate getDataDeFundacao() {
		return dataDeFundacao;
	}

	public void setDataDeFundacao(LocalDate dataDeFundacao) {
		this.dataDeFundacao = dataDeFundacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Long populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		States other = (States) obj;
		return Objects.equals(id, other.id);
	}

}
