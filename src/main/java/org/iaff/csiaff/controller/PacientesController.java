package org.iaff.csiaff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.iaff.csiaff.controller.page.PageWrapper;
import org.iaff.csiaff.model.Paciente;
import org.iaff.csiaff.repository.Pacientes;
import org.iaff.csiaff.repository.filter.PacienteFilter;
import org.iaff.csiaff.service.CadastroPacienteService;
import org.iaff.csiaff.service.exception.ImpossivelExcluirEntidadeException;
import org.iaff.csiaff.service.exception.PacientePessoaJaCadastradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
public class PacientesController {
	
	@Autowired
	private CadastroPacienteService cadastroPacienteService;
	
	@Autowired
	private Pacientes pacientes;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Paciente paciente) {
		ModelAndView mv = new ModelAndView("paciente/CadastroPaciente");
		return mv;
	}
	
	@PostMapping({ "/novo", "{\\+d}" })
	public ModelAndView salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(paciente);
		}
		
		try {
			cadastroPacienteService.salvar(paciente);
		} catch (PacientePessoaJaCadastradaException e) {
			result.rejectValue("Pessoa", e.getMessage(), e.getMessage());
			return novo(paciente);
		}
		
		attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso");
		return new ModelAndView("redirect:/pacientes/novo");
	}	
	
	@GetMapping
	public ModelAndView pesquisar(PacienteFilter pacienteFilter
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/paciente/PesquisaPacientes");
		
		PageWrapper<Paciente> paginaWrapper = new PageWrapper<>(pacientes.filtrar(pacienteFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Paciente paciente = pacientes.findOne(codigo);
		ModelAndView mv = novo(paciente);
		mv.addObject(paciente);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Paciente paciente) {
		try {
			cadastroPacienteService.excluir(paciente);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	// pesquisa rapida
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Paciente> pesquisar(String nome) {
		// return pessoas.findByNomeStartingWithIgnoreCase(nome);
		PacienteFilter filtro = new PacienteFilter();
		filtro.setNome(nome);
		
		//List<Paciente> pc = pacientes.filtrarPesquisaRapida(filtro);
		
		return pacientes.filtrarPesquisaRapida(filtro);
	}
	
	
}