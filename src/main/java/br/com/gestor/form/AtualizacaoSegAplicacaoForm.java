package br.com.gestor.form;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.gestor.model.SegAplicacao;
import br.com.gestor.repository.SegAplicacaoRepository;

public class AtualizacaoSegAplicacaoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String url;
	
	@NotNull @NotEmpty @Length(min = 5)
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

	public SegAplicacao atualizar(Long id, SegAplicacaoRepository aplicacaoRepository) {
		SegAplicacao aplicacao = aplicacaoRepository.getById(id);
		aplicacao.setUrl(this.url);
		aplicacao.setDescricao(this.descricao);
		return aplicacao;
	}
	
	
}
