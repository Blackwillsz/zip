package br.com.gestor.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


public class SegMenuForm {
	
	@NotBlank
	private String nome;
	private Long idSegMenuPai;
	private Long idSegAplicacao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getIdSegMenuPai() {
		return idSegMenuPai;
	}
	public void setIdSegMenuPai(Long idSegMenuPai) {
		this.idSegMenuPai = idSegMenuPai;
	}
	public Long getIdSegAplicacao() {
		return idSegAplicacao;
	}
	public void setIdSegAplicacao(Long idSegAplicacao) {
		this.idSegAplicacao = idSegAplicacao;
	}

	
}
