package br.com.gestor.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gestor.model.SegPerfilAplicacao;

public class DetalhesDoSegPerfilAplicacaoDto {
	
	private Long id;
	private Long idPerfil;
	private Long idAplicacao;
	private List<SegPerfilDto> listaPerfil;
	
	public DetalhesDoSegPerfilAplicacaoDto(SegPerfilAplicacao perfilAplicacao) {
		this.id = perfilAplicacao.getId();
		this.idPerfil = perfilAplicacao.getSegPerfil().getId();
		this.idAplicacao = perfilAplicacao.getSegAplicacao().getId();
		this.listaPerfil = new ArrayList<>();
		this.listaPerfil.addAll(perfilAplicacao.getListaPerfil().stream().map(SegPerfilDto::new).collect(Collectors.toList()));
	}
	
	public Long getId() {
		return id;
	} 
	
	public void setId(Long id) {
		this.id = id;
	} 
	
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

	public List<SegPerfilDto> getListaPerfil() {
		return listaPerfil;
	}
	

}
