package br.com.gestor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestor.form.AtualizarSegMenuForm;
import br.com.gestor.form.SegMenuForm;
import br.com.gestor.model.SegMenu;
import br.com.gestor.service.SegMenuService;

@RestController
@RequestMapping("/segMenu")
public class SegMenuController {
	
	@Autowired
	private SegMenuService segMenuService;
	
	@GetMapping
	public List<SegMenu> buscarMenu(){
		return segMenuService.buscarTodosMenu();
	}
	
	@PostMapping
	public ResponseEntity<Object> salvarMenu(@Valid @RequestBody SegMenuForm segMenuForm){
		return ResponseEntity.status(HttpStatus.CREATED).body(segMenuService.converterForm(segMenuForm));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarMenu(@PathVariable Long id, @Valid @RequestBody AtualizarSegMenuForm segMenuForm){
		return ResponseEntity.status(HttpStatus.OK).body(segMenuService.atualizarMenu(id, segMenuForm));
	}
	
	@DeleteMapping("/{id}")
	public void deletarMenu(@PathVariable Long id){
		segMenuService.deletarMenu(id);
	}

}
