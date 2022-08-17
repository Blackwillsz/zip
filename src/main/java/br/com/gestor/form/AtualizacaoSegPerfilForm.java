package br.com.gestor.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.gestor.model.SegPerfil;
import br.com.gestor.repository.SegPerfilRepository;

public class AtualizacaoSegPerfilForm {
	
	@NotBlank @Length(min = 5)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public SegPerfil atualizar(Long id, SegPerfilRepository repository) {
		SegPerfil perfil = repository.getById(id);
		perfil.setDescricao(this.descricao);
		return perfil;
	}

	@Override
	public String toString() {
		return "AtualizacaoSegPerfilForm [descricao=" + descricao + "]";
	}

}
