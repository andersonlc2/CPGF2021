package model.entities;

public class UnidadeGestora extends Entidade {

	private Integer codOrgao;
	
	public UnidadeGestora(Integer cod, String nome, Integer CodOrgao) {
		super(cod, nome);
		this.setCodOrgao(CodOrgao);
	}

	public Integer getCodOrgao() {
		return codOrgao;
	}

	public void setCodOrgao(Integer codOrgao) {
		this.codOrgao = codOrgao;
	}
}
