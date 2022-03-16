package br.com.gestor.form;

import br.com.gestor.model.SegAplicacao;

public class SegAplicacaoForm {
	
	private String url;
	
	private String descricao;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SegAplicacao converter() {
		return new SegAplicacao(url, descricao);
	}
	
	

}
