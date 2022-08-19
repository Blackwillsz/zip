package br.com.gestor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.gestor.form.AtualizacaoSegPerfilForm;
import br.com.gestor.form.SegPerfilForm;
import br.com.gestor.model.SegPerfil;
import br.com.gestor.repository.SegPerfilRepository;

@Service
public class SegPerfilService {

	@Autowired
	private SegPerfilRepository repository;

	public List<SegPerfil> buscarTodos() {
		return repository.findAll();
	}

	public Optional<SegPerfil> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public SegPerfil cadastrarPerfil(SegPerfil segPerfil) {
		return repository.save(segPerfil);
	}

//	@Transactional
//	public ResponseEntity<Object> atualizarPerfil(@Valid @RequestBody AtualizacaoSegPerfilForm form,
//			@PathVariable Long id) {
//		Optional<SegPerfil> atualizarPerfil = repository.findById(id);
//
//		System.out.println("##################" + form);
//		if (!atualizarPerfil.isPresent()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possivel atualizar perfil");
//		}
//
//		SegPerfil segPerfil = new SegPerfil();
//		BeanUtils.copyProperties(form, atualizarPerfil);
//		repository.save(segPerfil);
//
//		System.out.println("#################" + atualizarPerfil.get());
//		return ResponseEntity.status(HttpStatus.CREATED).body("Perfil atualizado");
//	}
	
	@Transactional
	public SegPerfil atualizarPerfil(SegPerfil segPerfil) {
		return repository.save(segPerfil);
	}
	
	

	public void deletarPorId(Long id) {
		repository.deleteById(id);
	}

}
