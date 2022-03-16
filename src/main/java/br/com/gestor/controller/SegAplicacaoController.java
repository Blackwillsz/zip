package br.com.gestor.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gestor.form.AtualizacaoSegAplicacaoForm;
import br.com.gestor.form.SegAplicacaoForm;
import br.com.gestor.model.SegAplicacao;
import br.com.gestor.service.SegAplicacaoService;

@RestController
@RequestMapping("/segAplicacao")
public class SegAplicacaoController {
	
	
	@Autowired
	private SegAplicacaoService service;
	
		
	@GetMapping
	public List<SegAplicacao> buscarAplicacao() {
		return service.buscarTodasAplicacao();
	}
	
	@GetMapping("/{id}")
	public SegAplicacao buscarAplicacaoPorId(@PathVariable Long id) {
		Optional<SegAplicacao> aplicacaoPorId = service.burcarAplicacaoPorId(id);
		return aplicacaoPorId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	@Transactional
	public ResponseStatusException cadastrar(@RequestBody @Valid SegAplicacaoForm form, UriComponentsBuilder builder) {
		service.cadastrarAplicacao(form);
		SegAplicacao aplicacao = new SegAplicacao();
		URI uri = builder.path("/segAplicacao/{id}").buildAndExpand(aplicacao.getId()).toUri();
		return new ResponseStatusException(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseStatusException atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoSegAplicacaoForm form){
		SegAplicacao aplicacao = service.atualizarAplicacao(id, form);
		return new ResponseStatusException(HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public void deletarAplicacao(@PathVariable Long id) {
		Optional<SegAplicacao> buscarAplicacao = Optional.ofNullable(service.burcarAplicacaoPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

		if (buscarAplicacao.isPresent()) {
			service.deletar(id);
		}
	}
	
	
	
	
	
//	@GetMapping
//	public String buscarAplicacao(Model model, SegAplicacao segAplicacao) {
//		Optional<SegAplicacao> aplicacaoPorId = aplicacaoRepository.findById(segAplicacao.getId());
//		model.addAttribute("descricao", aplicacaoPorId);
//		return aplicacaoPorId.toString();
//	}
	
//	@PostMapping
//	@Transactional
//	public ResponseEntity<SegAplicacaoDto> cadastrarAplicacao(@RequestBody @Valid SegAplicacaoForm form, UriComponentsBuilder uriBuilder) {
//		SegAplicacao segAplicacao = form.converter();
//		aplicacaoRepository.save(segAplicacao);
//		
//		URI uri = uriBuilder.path("/segAplicacao/{id}").buildAndExpand(segAplicacao.getId()).toUri();
//		return ResponseEntity.created(uri).body(new SegAplicacaoDto(segAplicacao));
//	}
	
//	@PutMapping("/{id}")
//	@Transactional
//	public ResponseEntity<SegAplicacaoDto> atualizarAplicacao(@PathVariable Long id, @RequestBody @Valid AtualizacaoSegAplicacaoForm form ){
//		service.atualizarAplicacao(id, form);
//		Optional<SegAplicacao> atualizarAplicacao = aplicacaoRepository.findById(id);
//		if (atualizarAplicacao.isPresent()) {
//			SegAplicacao atualizar = form.atualizar(id, aplicacaoRepository);
//			return ResponseEntity.ok(new SegAplicacaoDto(atualizar));
//		}
//		return ResponseEntity.notFound().build();
//	}
	
//	@GetMapping("/{id}")
//	@Transactional
//	public ResponseEntity<SegAplicacaoDto> detalhar(@PathVariable Long id) { 
//		Optional<SegAplicacao> detalharAplicacao = aplicacaoRepository.findById(id);
//		if (detalharAplicacao.isPresent()) {
//			return ResponseEntity.ok(new SegAplicacaoDto(detalharAplicacao.get()));
//		}
//		return ResponseEntity.notFound().build();
//	}
	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public ResponseStatusException deletar(@PathVariable Long id) {
//		if (service.deletar(id) == true){
//		 return new ResponseStatusException(HttpStatus.OK);
//		} else
//			return new ResponseStatusException(HttpStatus.BAD_REQUEST);
//	}

}
