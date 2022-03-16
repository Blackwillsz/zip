package br.com.gestor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestor.model.SegAplicacao;

public interface SegAplicacaoRepository extends JpaRepository<SegAplicacao, Long> {
	
	Optional<SegAplicacao> findById(Long id);
	
	
}
