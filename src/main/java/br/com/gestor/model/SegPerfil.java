package br.com.gestor.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "SEG_PERFIL")
@JsonIgnoreProperties
public class SegPerfil {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
	@SequenceGenerator(name = "idGenerator", sequenceName = "SEQ_SEG_PERFIL", allocationSize = 1)
	private Long id;

	@Version
	private Long jversion;

	private String descricao;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "segPerfil", fetch = FetchType.LAZY, cascade = (CascadeType.ALL))
	private Set<SegPerfilAplicacao> segPerfilAplicacao;
	

	public SegPerfil(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public SegPerfil() {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	public Set<SegPerfilAplicacao> getSegPerfilAplicacao() {
		return segPerfilAplicacao;
	}

	public void setSegPerfilAplicacao(Set<SegPerfilAplicacao> segPerfilAplicacao) {
		this.segPerfilAplicacao = segPerfilAplicacao;
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
		SegPerfil other = (SegPerfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SegPerfil [id=" + id + ", jversion=" + jversion + ", descricao=" + descricao + "]";
	}

}
