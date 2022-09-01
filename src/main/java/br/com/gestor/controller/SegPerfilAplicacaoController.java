package br.com.gestor.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.com.gestor.dto.SegPerfilAplicacaoDto;
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
	public ResponseEntity<Object> cadastrarPerfilAplicacao(@RequestBody @Valid SegPerfilAplicacaoForm form){
		service.converterForm(form);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarPerfilAplicacaoForm form){
		SegPerfilAplicacaoDto retorno =  service.atualizarPerfilAplicacao(id, form);
		
		if(retorno != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Atualizado com Sucesso!");
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


