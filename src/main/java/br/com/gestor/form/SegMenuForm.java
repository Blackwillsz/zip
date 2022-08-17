package br.com.gestor.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SegMenuForm {
	
	@NotBlank
	private String nome;
	private Long idSegMenuPai;
	@NotNull
	private Long idSegAplicacao;

}
