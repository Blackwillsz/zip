package br.com.gestor.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gestor.dto.SegPerfilDto;
import br.com.gestor.form.AtualizarPerfilAplicacaoForm;
import br.com.gestor.form.SegPerfilAplicacaoForm;
import br.com.gestor.model.SegAplicacao;
import br.com.gestor.model.SegPerfil;
import br.com.gestor.model.SegPerfilAplicacao;
import br.com.gestor.service.SegPerfilaplicacaoService;

@RestController
@RequestMapping("/segPerfilAplicacao")
public class SegPerfilAplicacaoController {
	
	
	@Autowired
	private SegPerfilaplicacaoService service;
	
	
	@GetMapping
	public List<SegPerfilAplicacao> buscarTodos(){
		return service.buscarPerfilAplicacao();
	}
	
	@GetMapping("/{id}")
	public Optional<SegPerfilAplicacao> buscarPorId(@PathVariable Long id) {
		return service.listarPorId(id);
	}
	
	@GetMapping("/buscarPerfil")
	public Page<SegPerfilDto> buscarPerfil(@RequestParam(required = false) Long id,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return service.buscarPerfil(id, paginacao);
	}
	
	@PostMapping
	@Transactional
	public ResponseStatusException cadastrar(@RequestBody @Valid SegPerfilAplicacaoForm form, Optional<SegPerfil> perfil, 
			Optional<SegAplicacao> aplicacao, UriComponentsBuilder uriBuilder) {
		
		Optional<SegPerfil> segPerfil = service.perfilPorId(form.getIdPerfil());
		Optional<SegAplicacao> segAplicacao = service.aplicacaoPorId(form.getIdAplicacao());
		
		if (segPerfil.isPresent() && segAplicacao.isPresent()) {
		service.cadastrarPerfilAplicacao(form, segPerfil, segAplicacao);
		SegPerfilAplicacao perfilAplicacao = new SegPerfilAplicacao();
		URI uri = uriBuilder.path("/segPerfilAplicacao/{id}").buildAndExpand(perfilAplicacao.getId()).toUri();
		return new ResponseStatusException(HttpStatus.CREATED);
		} else {
			return new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarPerfilAplicacaoForm form){
		SegPerfilAplicacao retorno =  service.atualizarPerfilAplicacao(id, form);
		
		if(retorno !=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Atualizado com Sucesso!");
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Verificar as Informações");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		Optional<SegPerfilAplicacao> retorno =  service.buscarPorId(id);
		
		if(!retorno.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Encontrado ");
		}
		service.deletar(retorno.get());
			return ResponseEntity.status(HttpStatus.OK).body("Excluido com Sucesso");
	}
}


