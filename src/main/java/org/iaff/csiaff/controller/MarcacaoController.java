package org.iaff.csiaff.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.iaff.csiaff.controller.page.PageWrapper;
import org.iaff.csiaff.model.Agenda;
import org.iaff.csiaff.model.SituacaoAtendimento;
import org.iaff.csiaff.repository.Agendas;
import org.iaff.csiaff.repository.Grupos;
import org.iaff.csiaff.repository.Pacientes;
import org.iaff.csiaff.repository.filter.AgendaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/marcacao")
public class MarcacaoController {
	
	@Autowired
	private Agendas agendas;
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private Pacientes pacientes;

	@GetMapping
	public ModelAndView pesquisar(AgendaFilter agendaFilter
			, @PageableDefault(size = 8) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/marcacao/PesquisaHorarios");
		mv.addObject("grupos", grupos.findByRegistroprofissionalNotNull());
		mv.addObject("todasSituacoes", SituacaoAtendimento.values());
		
		if(agendaFilter.getDataAgendamento() == null){
			agendaFilter.setDataAgendamento(LocalDateTime.now().toLocalDate());	
		}
		
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
		agenda.setSituacaoAtendimento(SituacaoAtendimento.AGE); // Agendado

		// System.out.println(SituacaoAtendimento.AGE.getDescricao() + SituacaoAtendimento.AGE);
		
		agendas.save(agenda);
		
		return ResponseEntity.ok().build();
	}
	
	
	// desmarcar agenda
	@GetMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void desmarcar(@PathVariable Long codigo) {
		
		Agenda agenda = agendas.findOne(codigo);
		agenda.setPaciente(null);
		agenda.setSituacaoAtendimento(null);
		
		agendas.save(agenda);
	}

	
	
}