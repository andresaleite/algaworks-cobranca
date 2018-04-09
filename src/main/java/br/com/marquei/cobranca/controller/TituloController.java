package br.com.marquei.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.marquei.cobranca.dominio.EnumStatusTitulo;
import br.com.marquei.cobranca.model.Titulo;
import br.com.marquei.cobranca.repository.Titulos;
import br.com.marquei.cobranca.repository.filter.TituloFilter;
import br.com.marquei.cobranca.service.CadastroTituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String PAGINA_CADASTRO = "CadastroTitulo" ;
	
	@Autowired
	private CadastroTituloService service;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(PAGINA_CADASTRO);
		mv.addObject(new Titulo());
		return mv;
	}
	
		/*	mÉTODO FUNCIONA PERFEITAMENTE, PROFESSOR VAI DAR OUTRO EXEMPLO
		 * @RequestMapping
			public ModelAndView pesquisar(@RequestParam(defaultValue="%") String descricao) {
		List<Titulo> listaTitulo = titulos.findByDescricaoContaining(descricao);		
			ModelAndView mv = new ModelAndView("PesquisaTitulo");
			mv.addObject("titulos",listaTitulo);
			return mv;
		}*/
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
		List<Titulo> listaTitulo = service.pesquisar(filtro);		
		ModelAndView mv = new ModelAndView("PesquisaTitulo");
		mv.addObject("titulos",listaTitulo);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors e, RedirectAttributes atributos) {
		if(e.hasErrors()) {
			return PAGINA_CADASTRO;
		}
		try {
			service.salvar(titulo);
			atributos.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException eIllegal) {
			e.rejectValue("data", null, eIllegal.getMessage());
			return PAGINA_CADASTRO;
		}
		
		
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView(PAGINA_CADASTRO);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes atributos) {
		service.excluir(codigo);
		atributos.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	@RequestMapping(value="/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		return service.receber(codigo);
	}
	
	@ModelAttribute("statusTitulo")
	public List<EnumStatusTitulo> statusTitulo(){
		return Arrays.asList(EnumStatusTitulo.values());

	}
}
