package org.iaff.csiaff.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.iaff.csiaff.controller.page.PageWrapper;
import org.iaff.csiaff.model.Agenda;
import org.iaff.csiaff.repository.Agendas;
import org.iaff.csiaff.repository.Pacientes;
import org.iaff.csiaff.repository.filter.AgendaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/agendas")
public class AgendasController {
	
	@Autowired
	private Agendas agendas;
	
	@Autowired
	private Pacientes pacientes;
	
	@GetMapping("/{codigo}")
	public ModelAndView pesquisar(@PathVariable Long codigo, 
			@PageableDefault(size = 8) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/agenda/PesquisaAgendas");
		
		AgendaFilter agendaFilter = new AgendaFilter();
		agendaFilter.setCodigo(codigo);
		agendaFilter.setDataAgendamento(LocalDateTime.now().toLocalDate());
		
		PageWrapper<Agenda> paginaWrapper = new PageWrapper<>(agendas.filtrar(agendaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	
	// marcação de consulta
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> marcarConsulta(Long codigoAgenda, Long codigoPaciente) {

		Agenda agenda = agendas.findOne(codigoAgenda);
		agenda.setPaciente(pacientes.findOne(codigoPaciente));

		agendas.save(agenda);
		
		return ResponseEntity.ok().build();
	}
	
}