package br.com.gestor.dto;

import org.springframework.data.domain.Page;

import br.com.gestor.model.SegPerfil;

public class SegPerfilDto {

	private Long id;
	private String descricao;

	public SegPerfilDto(SegPerfil segPerfil) {
		this.id = segPerfil.getId();
		this.descricao = segPerfil.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public static Page<SegPerfilDto> converter(Page<SegPerfil> segPerfi){
		return segPerfi.map(SegPerfilDto::new);
	}

	
	
	
}
