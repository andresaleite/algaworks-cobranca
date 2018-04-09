package br.com.marquei.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.marquei.cobranca.dominio.EnumStatusTitulo;
import br.com.marquei.cobranca.model.Titulo;
import br.com.marquei.cobranca.repository.Titulos;
import br.com.marquei.cobranca.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo t) {
		
		try {
			titulos.save(t);
		} catch (DataIntegrityViolationException ed) {
			throw new IllegalArgumentException("Formato de data inválido.");
		}
		
	}
	
	public void excluir(Long id) {
		
		try {
			titulos.deleteById(id);
		} catch (DataIntegrityViolationException ed) {
			throw new IllegalArgumentException("Não foi possível deletar.");
		}
		
	}
	
	public String receber(Long codigo) {		
		try {
			Titulo t =	titulos.getOne(codigo);
			t.setStatus(EnumStatusTitulo.RECEBIDO);
			titulos.save(t);
		} catch (DataIntegrityViolationException ed) {
			throw new IllegalArgumentException("Não foi possível receber.");
		}
		
		return EnumStatusTitulo.RECEBIDO.getDescricao();
		
	}
	
	public List<Titulo> pesquisar(TituloFilter filtro){
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return titulos.findByDescricaoContaining(descricao);
	}
}
