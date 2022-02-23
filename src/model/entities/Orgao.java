package model.entities;

public class Orgao extends Entidade {

	private Integer codOrgaoSup;
	
	public Orgao(Integer cod, String nome, Integer codOrgaoSup) {
		super(cod, nome);
		this.setCodOrgaoSup(codOrgaoSup);
	}

	public Integer getCodOrgaoSup() {
		return codOrgaoSup;
	}

	public void setCodOrgaoSup(Integer codOrgaoSup) {
		this.codOrgaoSup = codOrgaoSup;
	}
}
