package br.com.gestor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import br.com.gestor.api.ApiError;
import br.com.gestor.dto.SegPerfilAplicacaoDto;
import br.com.gestor.dto.SegPerfilDto;
import br.com.gestor.form.AtualizarPerfilAplicacaoForm;
import br.com.gestor.form.SegPerfilAplicacaoForm;
import br.com.gestor.model.SegAplicacao;
import br.com.gestor.model.SegPerfil;
import br.com.gestor.model.SegPerfilAplicacao;
import br.com.gestor.repository.SegAplicacaoRepository;
import br.com.gestor.repository.SegPerfilAplicacaoRepository;
import br.com.gestor.repository.SegPerfilRepository;

@Service
public class SegPerfilaplicacaoService {

	@Autowired
	private SegPerfilRepository perfilRepository;

	@Autowired
	private SegAplicacaoRepository aplicacaoRepository;

	@Autowired
	private SegPerfilAplicacaoRepository perfilAplicacaoRepository;

	@Autowired
	private ApiError error;

	public void deletarPorId(@Valid Long id) {
		perfilAplicacaoRepository.deleteById(id);
	}

	public Optional<SegPerfilAplicacao> buscarPorId(Long id) {
		return perfilAplicacaoRepository.findById(id);
	}

	public Optional<SegPerfil> perfilPorId(Long idPerfil) {
		return perfilRepository.findById(idPerfil);
	}

	public Optional<SegAplicacao> aplicacaoPorId(Long idAplicacao) {
		return aplicacaoRepository.findById(idAplicacao);
	}

	public List<SegPerfilAplicacao> buscarPerfilAplicacao() {
		return perfilAplicacaoRepository.findAll();
	}

	public Optional<SegPerfilAplicacao> listarPorId(Long id) {
		Optional<SegPerfilAplicacao> perfilAplicacao = perfilAplicacaoRepository.findById(id);
		return perfilAplicacao;
	}

	public Page<SegPerfilDto> buscarPerfil(Long id, Pageable paginacao) {
		Page<SegPerfil> perfilAplicacao = perfilRepository.findAll(paginacao);
		return SegPerfilDto.converter(perfilAplicacao);
	}

	@Transactional
	public SegPerfilAplicacaoDto atualizarPerfilAplicacao(@PathVariable Long id, @Valid
			AtualizarPerfilAplicacaoForm form) {

		Optional<SegPerfilAplicacao> perfilAplicacao = perfilAplicacaoRepository.findById(id);
		if (perfilAplicacao.isPresent()) {

			Optional<SegPerfil> segPerfil = perfilRepository.findById(form.getIdPerfil());
			Optional<SegAplicacao> segAplicacao = aplicacaoRepository.findById(form.getIdAplicacao());

			perfilAplicacao.get().setSegPerfil(new SegPerfil());
			perfilAplicacao.get().setSegAplicacao(new SegAplicacao());

			if (segPerfil.isPresent() && segAplicacao.isPresent()) {
				perfilAplicacao.get().setSegPerfil(segPerfil.get());
				perfilAplicacao.get().setSegAplicacao(segAplicacao.get());
			}
		}
		return new SegPerfilAplicacaoDto(
				perfilAplicacaoRepository.save(perfilAplicacao.isPresent() ? perfilAplicacao.get() : null));
	}

	@Transactional
	public void deletar(Long id) {
		Optional<SegPerfilAplicacao> buscarPerfilAplicacao = Optional.ofNullable(
				buscarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
		perfilAplicacaoRepository.deleteById(id);
	}

	@Transactional
	public SegPerfilAplicacaoDto salvarPerfilAplicacao(SegPerfilAplicacao perfilAplicacao) {
		return new SegPerfilAplicacaoDto(perfilAplicacaoRepository.save(perfilAplicacao));
	}

	public SegPerfilAplicacaoDto converterForm(@Valid SegPerfilAplicacaoForm form) {
		Optional<SegPerfil> segPerfil = perfilPorId(form.getIdPerfil());
		Optional<SegAplicacao> segAplicacao = aplicacaoPorId(form.getIdAplicacao());
		if (!segPerfil.isPresent() || !segAplicacao.isPresent()) {
			return null;
		}
		SegPerfilAplicacao perfilAplicacao = new SegPerfilAplicacao();
		perfilAplicacao.setSegPerfil(segPerfil.get());
		perfilAplicacao.setSegAplicacao(segAplicacao.get());
		return salvarPerfilAplicacao(perfilAplicacao);
	}

}
