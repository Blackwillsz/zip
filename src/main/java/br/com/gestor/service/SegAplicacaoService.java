package br.com.gestor.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

//	Get com parâmetro
	public Optional<SegAplicacao> burcarAplicacaoPorId(Long id) {
		return aplicacaoRepository.findById(id);
	}

//	Get sem parâmetro
	public List<SegAplicacao> buscarTodasAplicacao() {
		return aplicacaoRepository.findAll();
	}

//	Post
	public SegAplicacao cadastrarAplicacao(@Valid SegAplicacaoForm form) {
		SegAplicacao cadastrarAplicacao = new SegAplicacao();
		cadastrarAplicacao.setUrl(form.getUrl());
		cadastrarAplicacao.setDescricao(form.getDescricao());
		return aplicacaoRepository.save(cadastrarAplicacao);
	}

//	Put
	public SegAplicacao atualizarAplicacao(Long id, @Valid AtualizacaoSegAplicacaoForm form) {
		Optional<SegAplicacao> atualizarAplicacao = aplicacaoRepository.findById(id);
		try {
			if (!atualizarAplicacao.isPresent() && !form.getUrl().isEmpty()){
				return null;
			}
			atualizarAplicacao.get().setUrl(form.getUrl());

			
			
			if (!atualizarAplicacao.isPresent() && form.getDescricao().isEmpty()) {
				return null;
			}
			atualizarAplicacao.get().setDescricao(form.getDescricao());

		} catch (Exception e) {
			return null;
		}
		return aplicacaoRepository.save(atualizarAplicacao.get());
	}
	
	public void deletar(Long id) {
		aplicacaoRepository.deleteById(id);
	}

//	Delete
//	public boolean deletar(Long id) {
//		aplicacaoRepository.deleteById(id);
//		Optional<SegAplicacao> aplicacao = aplicacaoRepository.findById(id);
//		 if (aplicacao.get() == null) {
//			 return true;
//		 } else {
//			 return false;
//		 }
//	}
	
	
}
