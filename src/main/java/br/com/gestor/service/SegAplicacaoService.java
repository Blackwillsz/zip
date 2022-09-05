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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.gestor.api.ApiError;
import br.com.gestor.dto.SegAplicacaoDto;
import br.com.gestor.form.AtualizacaoSegAplicacaoForm;
import br.com.gestor.form.SegAplicacaoForm;
import br.com.gestor.model.SegAplicacao;
import br.com.gestor.repository.SegAplicacaoRepository;

@Service
public class SegAplicacaoService {

	@Autowired
	private SegAplicacaoRepository aplicacaoRepository;

	@Autowired
	private ApiError error;

	public Optional<SegAplicacao> burcarAplicacaoPorId(Long id) {
		return aplicacaoRepository.findById(id);
	}

	public List<SegAplicacao> buscarTodasAplicacao() {
		return aplicacaoRepository.findAll();
	}

	@Transactional
	public SegAplicacaoDto cadastrarAplicacao(@Valid SegAplicacaoForm aplicacaoForm) {
		SegAplicacao aplicacao = new SegAplicacao();
		BeanUtils.copyProperties(aplicacaoForm, aplicacao);
		return new SegAplicacaoDto(aplicacaoRepository.save(aplicacao));
	}

	@Transactional
	public SegAplicacaoDto atualizarAplicacao(@RequestBody @Valid Long id, SegAplicacaoForm aplicacaoForm) {
		Optional<SegAplicacao> segAplicacaoOptional = burcarAplicacaoPorId(id);

		if (!segAplicacaoOptional.isPresent()) {
			return new SegAplicacaoDto(null);
		}
		SegAplicacao segAplicacao = segAplicacaoOptional.get();
		BeanUtils.copyProperties(aplicacaoForm, segAplicacao, "id");
		segAplicacao.setId(segAplicacaoOptional.get().getId());
		return new SegAplicacaoDto(aplicacaoRepository.save(segAplicacao));
	}

	@Transactional
	public void deletar(Long id) {
		Optional<SegAplicacao> buscarAplicacao = Optional.ofNullable(
				burcarAplicacaoPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
		aplicacaoRepository.deleteById(id);
	}

}
