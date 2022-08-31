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
	public void cadastrarPerfilAplicacao(@Valid SegPerfilAplicacaoForm form, Optional<SegPerfil> perfil,
			Optional<SegAplicacao> aplicacao) {
		SegPerfilAplicacao perfilAplicacao = new SegPerfilAplicacao();
		perfilAplicacao.setSegPerfil(perfil.get());
		perfilAplicacao.setSegAplicacao(aplicacao.get());
		perfilAplicacaoRepository.save(perfilAplicacao);
	}

	public SegPerfilAplicacao atualizarPerfilAplicacao(@Valid @PathVariable Long id,
			AtualizarPerfilAplicacaoForm form) {

		Optional<SegPerfilAplicacao> perfilAplicacao = perfilAplicacaoRepository.findById(id);
		if (perfilAplicacao.isPresent()) {

			Optional<SegPerfil> segPerfil = perfilRepository.findById(form.getIdPerfil());
			Optional<SegAplicacao> segAplicaccao = aplicacaoRepository.findById(form.getIdAplicacao());

			if (segPerfil.isPresent()) {
				perfilAplicacao.get().setSegPerfil(new SegPerfil());
				perfilAplicacao.get().setSegPerfil(segPerfil.get());
			} else {
				return null;
			}

			if (segAplicaccao.isPresent()) {
				perfilAplicacao.get().setSegAplicacao(new SegAplicacao());
				perfilAplicacao.get().setSegAplicacao(segAplicaccao.get());
			} else {
				return null;
			}

		} else {
			return null;
		}
		return perfilAplicacaoRepository.save(perfilAplicacao.isPresent() ? perfilAplicacao.get() : null);
	}

	
	@Transactional
	public void deletar(SegPerfilAplicacao segPerfilAplicacao) {
			 perfilAplicacaoRepository.delete(segPerfilAplicacao);
	}

	@Transactional
	public SegPerfilAplicacaoDto salvarPerfilAplicacao(SegPerfilAplicacao perfilAplicacao) {
		return new SegPerfilAplicacaoDto(perfilAplicacaoRepository.save(perfilAplicacao));
	}

	public SegPerfilAplicacaoDto converterForm(@Valid SegPerfilAplicacaoForm form) {
		Optional<SegPerfil> segPerfil = perfilPorId(form.getIdPerfil());
		Optional<SegAplicacao> segAplicacao = aplicacaoPorId(form.getIdAplicacao());
		
		if (!segPerfil.isPresent() && !segAplicacao.isPresent()) {
			 return null;
		}
		SegPerfilAplicacao perfilAplicacao = new SegPerfilAplicacao();
		perfilAplicacao.setSegPerfil(segPerfil.get());
		perfilAplicacao.setSegAplicacao(segAplicacao.get());
		return salvarPerfilAplicacao(perfilAplicacao);
	}


}
