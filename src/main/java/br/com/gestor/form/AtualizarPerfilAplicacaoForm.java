package br.com.gestor.form;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import br.com.gestor.model.SegAplicacao;
import br.com.gestor.model.SegPerfil;
import br.com.gestor.model.SegPerfilAplicacao;
import br.com.gestor.repository.SegAplicacaoRepository;
import br.com.gestor.repository.SegPerfilAplicacaoRepository;
import br.com.gestor.repository.SegPerfilRepository;

public class AtualizarPerfilAplicacaoForm {
	
	private Long idPerfil;
	
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
	
	
	public SegPerfilAplicacao atualizar(Long id, SegPerfilAplicacaoRepository perfilAplicacaoRepository, SegPerfilRepository perfilRepository, SegAplicacaoRepository aplicacaoRepository) {
		SegPerfilAplicacao perfilAplicacao = perfilAplicacaoRepository.getById(id);
		
		Optional<SegPerfil>  perfil = perfilRepository.findById(getIdPerfil());
		Optional<SegAplicacao>  aplicacao = aplicacaoRepository.findById(getIdAplicacao());
		
		perfilAplicacao.setSegAplicacao(new SegAplicacao());;
		perfilAplicacao.setSegPerfil(new SegPerfil());
		if(perfil.isPresent()) {
			
			perfilAplicacao.setSegPerfil(perfil.get());
		}
		perfilAplicacao.setSegAplicacao(aplicacao.isPresent() ? aplicacao.get() : null);
		return perfilAplicacao;
	}
	@Override
	public String toString() {
		return "AtualizarPerfilAplicacaoForm [idPerfil=" + idPerfil + ", idAplicacao=" + idAplicacao + "]";
	}
	
	
	
	
	
	
	

}
