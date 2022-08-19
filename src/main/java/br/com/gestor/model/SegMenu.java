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

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	@OneToMany(mappedBy = "segMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SegAplicacao> idSegAplicacao;
	

}
