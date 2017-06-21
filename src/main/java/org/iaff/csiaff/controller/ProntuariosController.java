package org.iaff.csiaff.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.iaff.csiaff.controller.page.PageWrapper;
import org.iaff.csiaff.model.ItemProntuario;
import org.iaff.csiaff.model.TipoItem;
import org.iaff.csiaff.repository.Grupos;
import org.iaff.csiaff.repository.ItensProntuario;
import org.iaff.csiaff.repository.filter.ItemProntuarioFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/prontuarios")
public class ProntuariosController {
	
	@Autowired
	private ItensProntuario itensProntuario;
	
	@Autowired
	private Grupos grupos;

	@GetMapping("/{codigo}")
	public ModelAndView pesquisar(@PathVariable Long codigo
			, @PageableDefault(size = 15) Pageable pageable, HttpServletRequest httpServletRequest) {
		
		ItemProntuarioFilter itemProntuarioFilter = new ItemProntuarioFilter();
		itemProntuarioFilter.setCodigoPaciente(codigo);
		itemProntuarioFilter.setNomePaciente(httpServletRequest.getParameterValues("nome")[0]);
		
		ModelAndView mv = new ModelAndView("/prontuario/PesquisaProntuarios");
		mv.addObject("grupos", grupos.findByAnamnese(true));
		mv.addObject("itemProntuarioFilter", itemProntuarioFilter);
		mv.addObject("tiposItem", Arrays.asList(TipoItem.values()));
		
		PageWrapper<ItemProntuario> paginaWrapper = new PageWrapper<>(itensProntuario.filtrar(itemProntuarioFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	

	@GetMapping
	public ModelAndView pesquisarFiltro(ItemProntuarioFilter itemProntuarioFilter
			, @PageableDefault(size = 15) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/prontuario/PesquisaProntuarios");
		mv.addObject("grupos", grupos.findByAnamnese(true));
		mv.addObject("tiposItem", Arrays.asList(TipoItem.values()));
		
		PageWrapper<ItemProntuario> paginaWrapper = new PageWrapper<>(itensProntuario.filtrar(itemProntuarioFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

}