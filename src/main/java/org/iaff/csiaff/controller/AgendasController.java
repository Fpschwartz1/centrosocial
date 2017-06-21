package org.iaff.csiaff.controller;

import javax.servlet.http.HttpServletRequest;

import org.iaff.csiaff.controller.page.PageWrapper;
import org.iaff.csiaff.model.Agenda;
import org.iaff.csiaff.repository.Agendas;
import org.iaff.csiaff.repository.filter.AgendaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/agendas")
public class AgendasController {
	
	@Autowired
	private Agendas agendas;
	
	@GetMapping("/{codigo}")
	public ModelAndView pesquisar(@PathVariable Long codigo, 
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/agenda/PesquisaAgendas");
		
		AgendaFilter agendaFilter = new AgendaFilter();
		agendaFilter.setCodigo(codigo);
		
		PageWrapper<Agenda> paginaWrapper = new PageWrapper<>(agendas.filtrar(agendaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	
	
}