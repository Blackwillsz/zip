package br.com.gestor.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "SEG_MENU")
@JsonIgnoreProperties
public class SegMenu {
	
//	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SegMenu.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
	@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_MENU", allocationSize = 1)
	private Long id;
	
	@Version
	private Long jversion;
	private String nome;
	private Long idSegMenuPai;
	
	@JsonManagedReference
//	@OneToMany(mappedBy = "segMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_seg_aplicacao", referencedColumnName = "id")
	private SegAplicacao idSegAplicacao;
	
	public SegMenu() {}
	
	public SegMenu(Long id, String nome, Long idSegMenuPai) {
		this.id = id;
		this.nome = nome;
		this.idSegMenuPai = idSegMenuPai;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SegAplicacao getIdSegAplicacao() {
		return idSegAplicacao;
	}

	public void setIdSegAplicacao(SegAplicacao idSegAplicacao) {
		this.idSegAplicacao = idSegAplicacao;
	}



}
