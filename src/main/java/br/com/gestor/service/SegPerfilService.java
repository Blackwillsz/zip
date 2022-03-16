package br.com.gestor.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public SegPerfil cadastrarPerfil(@Valid SegPerfilForm form) {
		SegPerfil cadastrar = new SegPerfil();
		cadastrar.setDescricao(form.getDescricao());
		return repository.save(cadastrar);
	}

	public SegPerfil atualizarPerfil(@Valid AtualizacaoSegPerfilForm form, Long id) {
		Optional<SegPerfil> atualizarPerfil = repository.findById(id);
		try {
			if (!atualizarPerfil.isPresent()) {
				return null;
			}
			atualizarPerfil.get().setDescricao(form.getDescricao());
			
		} catch (Exception e) {
			 Optional.ofNullable(atualizarPerfil).orElseThrow(IllegalArgumentException::new);
		}
		return repository.save(atualizarPerfil.get());
	}

	public void deletarPorId(Long id) {
		repository.deleteById(id);
	}
	
	
}


