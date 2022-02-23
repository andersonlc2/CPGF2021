package model.entities;

public class Transacao {

	private Integer cod;
	private String data;
	private Integer ano;
	private Double valor;
	private String descricao;
	private Integer codPortador;
	private Long codFavorecido;
	private Integer codOrgao;
	private String mes;
	
	public Transacao(Integer cod, String data, Integer ano, Double valor, String descricao, Integer codPortador,
			Long codFavorecido, Integer codOrgao, String mes) {
		this.cod = cod;
		this.data = data;
		this.ano = ano;
		this.valor = valor;
		this.descricao = descricao;
		this.codPortador = codPortador;
		this.codFavorecido = codFavorecido;
		this.codOrgao = codOrgao;
		this.mes = mes;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCodPortador() {
		return codPortador;
	}

	public void setCodPortador(Integer codPortador) {
		this.codPortador = codPortador;
	}

	public Long getCodFavorecido() {
		return codFavorecido;
	}

	public void setCodFavorecido(Long codFavorecido) {
		this.codFavorecido = codFavorecido;
	}

	public Integer getCodOrgao() {
		return codOrgao;
	}

	public void setCodOrgao(Integer codOrgao) {
		this.codOrgao = codOrgao;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	
	
}
