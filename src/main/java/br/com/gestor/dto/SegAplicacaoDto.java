package br.com.gestor.dto;

import br.com.gestor.model.SegAplicacao;

public class SegAplicacaoDto {
	
	private Long id;
	
	private String url;
	
	private String descricao;
	
	public SegAplicacaoDto(SegAplicacao segAplicacao) {
		this.id = segAplicacao.getId();
		this.url = segAplicacao.getUrl();
		this.descricao = segAplicacao.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	

}
