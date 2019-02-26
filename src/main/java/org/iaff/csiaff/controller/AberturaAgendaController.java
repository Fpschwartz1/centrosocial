package org.iaff.csiaff.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.iaff.csiaff.repository.Agendas;
import org.iaff.csiaff.repository.Grupos;
import org.iaff.csiaff.repository.filter.AgendaFilter;
import org.iaff.csiaff.service.AberturaAgendaService;
import org.iaff.csiaff.service.exception.AbrirAgendaEmDataPassadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/abriragenda")
public class AberturaAgendaController {
	
	@Autowired
	private Agendas agendas;
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private AberturaAgendaService aberturaAgendaService;
	
	@GetMapping
	public ModelAndView pesquisar(AgendaFilter agendaFilter, 
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/abriragenda/PesquisaProfissionalSemAgenda");
		mv.addObject("grupos", grupos.findByRegistroprofissionalNotNull());
		
		if(agendaFilter.getDataAgendamento() == null){
			agendaFilter.setDataAgendamento(LocalDateTime.now().toLocalDate());	
		}
		
		// Ajusta o título do campo profissional com dados da pesquisa 
		if(agendaFilter.getGrupo() != null){
			mv.addObject("tituloPesquisa", "Profissional - ".concat(grupos.getOne(agendaFilter.getGrupo().getCodigo()).getNome()).concat(" - ").concat(agendaFilter.getDataAgendamentoString()));	
		} else {
			mv.addObject("tituloPesquisa", "Profissional - ".concat(agendaFilter.getDataAgendamentoString()) );
		}
		
		mv.addObject("usuarios", agendas.filtrar2(agendaFilter));
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView abriragenda(@PathVariable Long codigo,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/abriragenda/PesquisaProfissionalSemAgenda");
		mv.addObject("grupos", grupos.findByRegistroprofissionalNotNull());
		
		AgendaFilter agendaFilter = new AgendaFilter();
		agendaFilter.setDataAgendamento(LocalDate.parse(httpServletRequest.getParameterValues("dataAgendamento")[0].toString()));	
		mv.addObject(agendaFilter);

		// inserir agenda
		try{
			aberturaAgendaService.abriragenda(codigo, agendaFilter.getDataAgendamento());
		} catch (AbrirAgendaEmDataPassadaException e) {
			//attributes.addFlashAttribute("mensagem", e.getMessage());
			agendaFilter.setDataAgendamento(LocalDateTime.now().toLocalDate());			
		}
		
		mv.addObject("usuarios", agendas.filtrar2(agendaFilter));
		
		return mv;
	}
	
}