package br.com.gestor.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gestor.model.SegMenu;
import br.com.gestor.repository.SegMenuRepository;

@Service
public class SegMenuService {
	
	@Autowired
	private SegMenuRepository segMenuRepository;
	
	public List<SegMenu> buscarTodosMenu(){
		return segMenuRepository.findAll();
	}

	@Transactional
	public SegMenu salvarMenu(SegMenu segMenu) {
		return segMenuRepository.save(segMenu);
	}
	

}
