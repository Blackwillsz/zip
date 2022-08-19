package br.com.gestor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SEG_PERFIL_APLICACAO")
@JsonIgnoreProperties
public class SegPerfilAplicacao {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator  = "idGeneration")
	@SequenceGenerator(name = "idGeneration", sequenceName = "SEQ_SEG_PERFIL_APLICACAO", allocationSize = 1)
	private Long id;
	
	@Version
	@JsonIgnore
	private Long jversion;
	
	@JsonIgnore
	private Integer paginaInicial;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_seg_perfil", referencedColumnName = "id")
	private SegPerfil segPerfil;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_seg_aplicacao", referencedColumnName = "id")
	private SegAplicacao segAplicacao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "segPerfilAplicacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SegPerfil> listaPerfil = new ArrayList<>();
	
	public SegPerfilAplicacao() {}
	
	public SegPerfilAplicacao( SegPerfil perfil, SegAplicacao aplicacao) {
		this.segPerfil = perfil;
		this.segAplicacao = aplicacao;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJversion() {
		return jversion;
	}

	public void setJversion(Long jversion) {
		this.jversion = jversion;
	}

	public SegAplicacao getSegAplicacao() {
		return segAplicacao;
	}

	public void setSegAplicacao(SegAplicacao segAplicacao) {
		this.segAplicacao = segAplicacao;
	}

	public SegPerfil getSegPerfil() {
		return segPerfil;
	}

	public void setSegPerfil(SegPerfil segPerfil) {
		this.segPerfil = segPerfil;
	}

	public Integer getPaginaInicial() {
		return paginaInicial;
	}

	public void setPaginaInicial(Integer paginaInicial) {
		this.paginaInicial = paginaInicial;
	}
	
	public List<SegPerfil> getListaPerfil() {
		return listaPerfil;
	}
	
	public void setListaPerfil(List<SegPerfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SegPerfilAplicacao other = (SegPerfilAplicacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SegPerfilAplicacao [id=" + id + ", jversion=" + jversion + ", paginaInicial=" + paginaInicial
				+ ", segAplicacao=" + segAplicacao + ", segPerfil=" + segPerfil +  "]";
	}

	
	
	

}
