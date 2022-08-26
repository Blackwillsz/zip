package br.com.gestor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.gestor.dto.SegPerfilDto;
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
	public SegPerfilDto cadastrarPerfil(SegPerfil segPerfil) {
		return new SegPerfilDto(repository.save(segPerfil));
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
	public SegPerfilDto atualizarPerfil(SegPerfil segPerfil) {
		return new SegPerfilDto(repository.save(segPerfil));
	}
	
	

	public void deletarPorId(Long id) {
		repository.deleteById(id);
	}

}
