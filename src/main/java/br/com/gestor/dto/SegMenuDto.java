package br.com.gestor.dto;

import br.com.gestor.model.SegMenu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SegMenuDto {
	
	private String nome;
	private Long idSegMenuPai;
	private Long idSegAplicacao;

	public SegMenuDto(SegMenu segMenu) {
		this.nome = segMenu.getNome();
		this.idSegMenuPai = segMenu.getIdSegMenuPai();
	}

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
