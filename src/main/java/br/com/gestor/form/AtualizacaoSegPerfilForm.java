package br.com.gestor.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.gestor.model.SegPerfil;
import br.com.gestor.repository.SegPerfilRepository;

public class AtualizacaoSegPerfilForm {
	
	@NotNull @NotEmpty @Length(min = 5)
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

}
