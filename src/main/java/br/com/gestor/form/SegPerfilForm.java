package br.com.gestor.form;

import br.com.gestor.model.SegPerfil;

public class SegPerfilForm {
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public SegPerfil converter(){
		return new SegPerfil(); 
	}

}
