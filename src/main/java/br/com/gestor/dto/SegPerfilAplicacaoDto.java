package br.com.gestor.dto;

import java.util.Optional;

import org.springframework.data.domain.Page;

import br.com.gestor.model.SegAplicacao;
import br.com.gestor.model.SegPerfil;
import br.com.gestor.model.SegPerfilAplicacao;

public class SegPerfilAplicacaoDto {
	
	private Long id;
	private SegPerfil perfil;
	private SegAplicacao aplicacao;
	
	
	public SegPerfilAplicacaoDto(SegPerfilAplicacao perfilAplicacao) {
		this.id = perfilAplicacao.getId();
		this.perfil = perfilAplicacao.getSegPerfil();
		this.aplicacao = perfilAplicacao.getSegAplicacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SegPerfil getPerfil() {
		return perfil;
	}

	public void setPerfil(SegPerfil perfil) {
		this.perfil = perfil;
	}

	public SegAplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(SegAplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}
	
	public static Page<SegPerfilAplicacaoDto> converter(Page<SegPerfilAplicacao> perfilAplicacao){
		return perfilAplicacao.map(SegPerfilAplicacaoDto::new);
	}

	
	

}
