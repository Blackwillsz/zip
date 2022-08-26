package br.com.gestor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.gestor.api.ApiError;
import br.com.gestor.dto.SegAplicacaoDto;
import br.com.gestor.form.AtualizacaoSegAplicacaoForm;
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
	public SegAplicacaoDto cadastrarAplicacao(@Valid SegAplicacao segAplicacao) {
		return new SegAplicacaoDto(aplicacaoRepository.save(segAplicacao));
	}

	@Transactional
	public AtualizacaoSegAplicacaoForm atualizarAplicacao(@RequestBody @Valid SegAplicacao segAplicacao) {
		return new AtualizacaoSegAplicacaoForm(aplicacaoRepository.save(segAplicacao));
	}
	
	@Transactional
	public void deletar(Long id) {
		aplicacaoRepository.deleteById(id);
	}

	
	
}
