package br.com.gestor.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestor.model.SegPerfil;

public interface SegPerfilRepository extends JpaRepository<SegPerfil, Long>{

	Optional<SegPerfil> findById(SegPerfil idPerfil);
	
	Page<SegPerfil> findById(Long id, Pageable paginacao);
	
	
}
