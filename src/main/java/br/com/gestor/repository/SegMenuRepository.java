package br.com.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestor.model.SegMenu;

@Repository
public interface SegMenuRepository extends JpaRepository<SegMenu, Long>{

}
