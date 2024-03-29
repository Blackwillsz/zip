package br.com.gestor.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.gestor.dto.SegPerfilDto;
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
	public List<SegPerfil> buscarPerfil(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return service.buscarTodos(pageable);
	}

	@GetMapping("/{id}")
	public SegPerfil buscarPerfilPorId(@PathVariable Long id) {
		Optional<SegPerfil> perfilPorId = service.buscarPorId(id);
		return perfilPorId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Object> cadastrarPerfil(@RequestBody @Valid SegPerfilForm form) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPerfil(form));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarPerfil(@PathVariable(value = "id") Long id,
			@RequestBody @Valid AtualizacaoSegPerfilForm form) {
		return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPerfil(form, id));
	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		service.deletarPorId(id);

	}

}
