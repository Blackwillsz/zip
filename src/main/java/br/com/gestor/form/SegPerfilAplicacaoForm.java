package br.com.gestor.form;

import javax.validation.constraints.NotNull;

import br.com.gestor.model.SegAplicacao;
import br.com.gestor.model.SegPerfil;
import br.com.gestor.model.SegPerfilAplicacao;
import br.com.gestor.repository.SegAplicacaoRepository;
import br.com.gestor.repository.SegPerfilRepository;

public class SegPerfilAplicacaoForm {
	
	@NotNull 
	private Long idPerfil;
	
	@NotNull 
	private Long idAplicacao;
	
	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdAplicacao() {
		return idAplicacao;
	}

	public void setIdAplicacao(Long idAplicacao) {
		this.idAplicacao = idAplicacao;
	}


	public SegPerfilAplicacao converter(SegPerfilRepository perfilRepository,  SegAplicacaoRepository aplicacaoRepository) {
		
		SegPerfil perfil = perfilRepository.getById(idPerfil);
		SegAplicacao aplicacao = aplicacaoRepository.getById(idAplicacao);
		return new SegPerfilAplicacao(perfil, aplicacao);
	}
	
}
