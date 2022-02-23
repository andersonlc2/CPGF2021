package model.entities;

import java.util.Objects;

public abstract class Entidade {

	private Integer cod;
	private String nome;
	
	public Entidade(Integer cod, String nome) {
		this.cod = cod;
		this.nome = nome;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
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
		Entidade other = (Entidade) obj;
		return Objects.equals(cod, other.cod);
	}	
}
