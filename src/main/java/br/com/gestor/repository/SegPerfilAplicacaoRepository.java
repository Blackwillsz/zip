package br.com.gestor.repository;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestor.model.SegPerfilAplicacao;

public interface SegPerfilAplicacaoRepository extends JpaRepository<SegPerfilAplicacao, Long>{
	
	public Page<SegPerfilAplicacao> findById(Long id, Pageable paginacao);
	
	public Page<SegPerfilAplicacao> findAll(Pageable paginacao);

	public SegPerfilAplicacao save(ModelMapper modelMapper);
	
}
