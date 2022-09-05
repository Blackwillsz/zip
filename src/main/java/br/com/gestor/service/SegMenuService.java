package br.com.gestor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.gestor.dto.SegMenuDto;
import br.com.gestor.form.AtualizarSegMenuForm;
import br.com.gestor.form.SegMenuForm;
import br.com.gestor.model.SegAplicacao;
import br.com.gestor.model.SegMenu;
import br.com.gestor.repository.SegAplicacaoRepository;
import br.com.gestor.repository.SegMenuRepository;

@Service
public class SegMenuService {

	@Autowired
	private SegMenuRepository segMenuRepository;

	@Autowired
	private SegAplicacaoRepository aplicacaoRepository;

	public Optional<SegMenu> buscarPorId(Long id) {
		return segMenuRepository.findById(id);
	}

	private Optional<SegAplicacao> aplicacaoPorId(Long idSegAplicacao) {
		return aplicacaoRepository.findById(idSegAplicacao);
	}

	public List<SegMenu> buscarTodosMenu() {
		return segMenuRepository.findAll();
	}

	public SegMenuDto salvarMenu(SegMenu segMenu) {
		return new SegMenuDto(segMenuRepository.save(segMenu));
	}

	@Transactional
	public void deletarMenu(Long id) {
		Optional.ofNullable(buscarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
		segMenuRepository.deleteById(id);
	}

	@Transactional
	public SegMenuDto atualizarMenu(Long id, AtualizarSegMenuForm segMenuForm) {
		Optional<SegMenu> menuOptional = segMenuRepository.findById(id);

		if (menuOptional.isPresent()) {
			menuOptional.get().setIdSegAplicacao(new SegAplicacao());
			Optional<SegAplicacao> aplicacaoOptional = aplicacaoRepository.findById(segMenuForm.getIdSegAplicacao());
			if (aplicacaoOptional.isPresent()) {
				menuOptional.get().setNome(segMenuForm.getNome());
				menuOptional.get().setIdSegMenuPai(segMenuForm.getIdSegMenuPai());
				menuOptional.get().setIdSegAplicacao(aplicacaoOptional.get());
				return new SegMenuDto(segMenuRepository.save(menuOptional.isPresent() ? menuOptional.get() : null));
			}
			menuOptional.get().setNome(segMenuForm.getNome());
			menuOptional.get().setIdSegMenuPai(segMenuForm.getIdSegMenuPai());
			menuOptional.get().setIdSegAplicacao(aplicacaoOptional.get());
			return new SegMenuDto(segMenuRepository.save(menuOptional.isPresent() ? menuOptional.get() : null));
		}
		return new SegMenuDto(null);
	}

	@Transactional
	public SegMenuDto converterForm(SegMenuForm segMenuForm) {
		SegMenu menu = new SegMenu();
		menu.setIdSegAplicacao(buscarIdSegAplicacao(segMenuForm));
		menu.setNome(segMenuForm.getNome());
		menu.setIdSegMenuPai(segMenuForm.getIdSegMenuPai());
		return salvarMenu(menu);

	}

	private SegAplicacao buscarIdSegAplicacao(SegMenuForm segMenuForm) {
		if (segMenuForm.getIdSegAplicacao() != null) {
			Optional<SegAplicacao> segAplicacao = aplicacaoPorId(segMenuForm.getIdSegAplicacao());
			if (segAplicacao.isPresent()) {
				return segAplicacao.get();
			}
		}
		return null;
	}
}
