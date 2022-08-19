package br.com.gestor.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import br.com.gestor.api.ApiError;
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
	public SegAplicacao cadastrarAplicacao(@Valid SegAplicacao segAplicacao) {
		return aplicacaoRepository.save(segAplicacao);
	}

	@Transactional
	public SegAplicacao atualizarAplicacao(@RequestBody @Valid SegAplicacao segAplicacao) {
		return aplicacaoRepository.save(segAplicacao);
	}
	
	@Transactional
	public void deletar(Long id) {
		aplicacaoRepository.deleteById(id);
	}

	
	
}
