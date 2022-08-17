package br.com.gestor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		SegMenu segMenu = new SegMenu();
		BeanUtils.copyProperties(segMenuForm, segMenu);
		return ResponseEntity.status(HttpStatus.CREATED).body(segMenuService.salvarMenu(segMenu));
	}

}
