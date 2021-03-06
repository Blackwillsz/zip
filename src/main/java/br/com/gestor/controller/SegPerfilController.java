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

import br.com.gestor.form.AtualizacaoSegPerfilForm;
import br.com.gestor.form.SegPerfilForm;
import br.com.gestor.model.SegPerfil;
import br.com.gestor.service.SegPerfilService;

@RestController
@RequestMapping("/segPerfil")
public class SegPerfilController {

	@Autowired
	private SegPerfilService service;

	@GetMapping
	public List<SegPerfil> buscarPerfil() {
		return service.buscarTodos();
	}

	@GetMapping("/{id}")
	public SegPerfil buscarPerfilPorId(@PathVariable Long id) {
		Optional<SegPerfil> perfilPorId = service.buscarPorId(id);
		return perfilPorId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@Transactional
	public ResponseStatusException cadastrarPerfil(@RequestBody @Valid SegPerfilForm form, UriComponentsBuilder uriBuilder) {
		service.cadastrarPerfil(form);
		SegPerfil segPerfil = new SegPerfil();
		URI uri = uriBuilder.path("/segPerfil/{id}").buildAndExpand(segPerfil.getId()).toUri();
		return new ResponseStatusException(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseStatusException atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoSegPerfilForm form) {
		service.atualizarPerfil(form, id);
			return new ResponseStatusException(HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	@Transactional
	public void remover(@PathVariable Long id) {
		Optional<SegPerfil> deletarPerfil = Optional.ofNullable(service.buscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
		
		if (deletarPerfil.isPresent()) {
			service.deletarPorId(id);
		}
	}

}
