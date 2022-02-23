package model.entities;

import java.util.Objects;

public class Portador {

	private Integer cpf;
	private String nome;
	private Integer codUnidGestora;
	
	public Portador(Integer cpf, String nome, Integer codUnidGestora) {
		this.cpf = cpf;
		this.setNome(nome);
		this.codUnidGestora = codUnidGestora;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Integer getCodUnidGestora() {
		return codUnidGestora;
	}

	public void setCodUnidGestora(Integer codUnidGestora) {
		this.codUnidGestora = codUnidGestora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portador other = (Portador) obj;
		return Objects.equals(cpf, other.cpf);
	}
}
