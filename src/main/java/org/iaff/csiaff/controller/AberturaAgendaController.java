package org.iaff.csiaff.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.iaff.csiaff.repository.Agendas;
import org.iaff.csiaff.repository.Grupos;
import org.iaff.csiaff.repository.filter.AgendaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/abriragenda")
public class AberturaAgendaController {
	
	@Autowired
	private Agendas agendas;
	
	@Autowired
	private Grupos grupos;
	
	@GetMapping
	public ModelAndView pesquisar(AgendaFilter agendaFilter, 
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/abriragenda/PesquisaProfissionalSemAgenda");
		mv.addObject("grupos", grupos.findByRegistroprofissionalNotNull());
		
		if(agendaFilter.getDataAgendamento() == null){
			agendaFilter.setDataAgendamento(LocalDateTime.now().toLocalDate());	
		}
		
		mv.addObject("usuarios", agendas.filtrar2(agendaFilter));
		return mv;
	}
	
}