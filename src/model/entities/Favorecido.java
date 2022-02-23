package model.entities;

import java.util.Objects;

public class Favorecido {

	private Long cod;
	private String nome;
	
	public Favorecido(Long cod, String nome) {
		this.cod = cod;
		this.nome = nome;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Favorecido other = (Favorecido) obj;
		return Objects.equals(cod, other.cod);
	}

		
}
