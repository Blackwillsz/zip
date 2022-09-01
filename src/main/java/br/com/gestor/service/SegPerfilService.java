package br.com.gestor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.gestor.dto.SegPerfilDto;
import br.com.gestor.form.AtualizacaoSegPerfilForm;
import br.com.gestor.form.SegPerfilForm;
import br.com.gestor.model.SegPerfil;
import br.com.gestor.repository.SegPerfilRepository;

@Service
public class SegPerfilService {

	@Autowired
	private SegPerfilRepository repository;

	public List<SegPerfil> buscarTodos(Pageable pageable) {
		return repository.findAll();
	}

	public Optional<SegPerfil> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public SegPerfilDto cadastrarPerfil(SegPerfilForm form) {
		SegPerfil segPerfil = new SegPerfil();
		BeanUtils.copyProperties(form, segPerfil);
		return new SegPerfilDto(repository.save(segPerfil));
	}

	@Transactional
	public SegPerfilDto atualizarPerfil(AtualizacaoSegPerfilForm form, Long id) {
		Optional<SegPerfil> segPerfilOptional = buscarPorId(id);

		if (segPerfilOptional.isPresent()) {
			SegPerfil segPerfil = segPerfilOptional.get();
			BeanUtils.copyProperties(form, segPerfil);
			segPerfil.setId(segPerfilOptional.get().getId());
			return new SegPerfilDto(repository.save(segPerfil));
		}
		return new SegPerfilDto(null);
	}

	@Transactional
	public void deletarPorId(Long id) {
		Optional<SegPerfil> deletarPerfil = Optional
				.ofNullable(buscarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
		repository.deleteById(id);
	}

}
