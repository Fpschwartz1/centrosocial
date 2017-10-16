package org.iaff.csiaff.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.iaff.csiaff.controller.page.PageWrapper;
import org.iaff.csiaff.model.ItemProntuario;
import org.iaff.csiaff.model.OpcaoCampoItem;
import org.iaff.csiaff.model.Paciente;
import org.iaff.csiaff.model.TipoItem;
import org.iaff.csiaff.model.TipoItemProntuario;
import org.iaff.csiaff.model.Usuario;
import org.iaff.csiaff.repository.Grupos;
import org.iaff.csiaff.repository.ItensProntuario;
import org.iaff.csiaff.repository.OpcoesCampoItem;
import org.iaff.csiaff.repository.Pacientes;
import org.iaff.csiaff.repository.TiposItensProntuario;
import org.iaff.csiaff.repository.Usuarios;
import org.iaff.csiaff.repository.filter.ItemProntuarioFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/prontuarios")
public class ProntuariosController {
	
	@Autowired
	private ItensProntuario itensProntuario;
	
	@Autowired
	private TiposItensProntuario tiposItensProntuario;
	
	@Autowired
	private OpcoesCampoItem opcoesCampoItem;
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private Pacientes pacientes;

	@Autowired
	private Usuarios usuarios;
	
	@RequestMapping("/novo/{codigo}")
	public ModelAndView novo(@PathVariable Long codigo, HttpServletRequest httpServletRequest) {
		ItemProntuario itemProntuario = new ItemProntuario();
		
		Usuario usuario = usuarios.findOne(Long.parseLong(httpServletRequest.getParameterValues("codigoProfissional")[0]));
		
		itemProntuario.setPaciente(pacientes.findOne(codigo));
		itemProntuario.setUsuario(usuario);

		return novo(itemProntuario);
	}
	
	public ModelAndView novo(ItemProntuario itemProntuario) {
		ModelAndView mv = new ModelAndView("prontuario/CadastroItemProntuario");
		
		mv.addObject(itemProntuario);
		mv.addObject("tiposItens", TipoItem.values());
		
		return mv;
	}
	
	@RequestMapping(value = "/tipos", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TipoItemProntuario> pesquisarTipoItemProntuario(
			@RequestParam(name = "tipoItem", defaultValue = "A") String tipoItem,
			@RequestParam(name = "codigoGrupo", defaultValue = "A") Long codigoGrupo){
		
		TipoItem ti = TipoItem.valueOf(tipoItem);
		
		if(tipoItem.equals("A")){
			return tiposItensProntuario.findByTipoItem(ti);	
		} else {
			return tiposItensProntuario.findByGrupoAndTipoItem(grupos.findOne(codigoGrupo), ti);
		}
	}
	

	@RequestMapping(value = "/opcoes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<OpcaoCampoItem> pesquisarOpcaoCampoItem(
			@RequestParam(name = "codigoTipoItemProntuario") Long codigoTipoItemProntuario){
		
		return opcoesCampoItem.findByTipoItemProntuario(tiposItensProntuario.findOne(codigoTipoItemProntuario));
		
	}


	// chamado a partir da lista da agenda 
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
	
	// chamado quando os filtros são selecionados na pesquisa 
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
	
	@PostMapping({"/novo"}) //, "{\\+d}" })
	public ModelAndView salvar(@Valid ItemProntuario itemProntuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(itemProntuario);
		}

		itemProntuario.setDataLancamento(LocalDateTime.now());
		
		itemProntuario.setNomeGrupo(itemProntuario.getUsuario().getGrupos().get(0).getNome());
		
		itensProntuario.save(itemProntuario);
		
		Paciente paciente = itemProntuario.getPaciente();
		
		return new ModelAndView("redirect:/prontuarios/".concat(paciente.getCodigo().toString().concat("?nome=").concat(paciente.getPessoa().getNome())));
	}
}