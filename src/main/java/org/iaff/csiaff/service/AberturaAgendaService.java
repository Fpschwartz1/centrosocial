package org.iaff.csiaff.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.iaff.csiaff.model.Agenda;
import org.iaff.csiaff.model.Usuario;
import org.iaff.csiaff.repository.Agendas;
import org.iaff.csiaff.repository.Usuarios;
import org.iaff.csiaff.service.exception.AbrirAgendaEmDataPassadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AberturaAgendaService {

	@Autowired
	private Agendas agendas;
	
	@Autowired
	private Usuarios usuarios;

	@Transactional
	public void abriragenda(Long codigo, LocalDate dataAgendamento) {
				
		if (dataAgendamento.compareTo(LocalDateTime.now().toLocalDate()) < 0) {
			throw new AbrirAgendaEmDataPassadaException("A data não pode ser anterior ao dia de hoje.");
		}
		
		Usuario usuario = usuarios.getOne(codigo);
		
		int duracao = usuario.getDuracaoConsulta();
		
		int ini = 8, fim = 14;
				
		for(int i = ini; i < fim; i++){
		
			for(int j = 0; j < 60; j+=duracao){

				Agenda agenda = new Agenda();
				agenda.setUsuario(usuario);
			
				agenda.setDataAgendamento(dataAgendamento);
				agenda.setHoraAgendamento(String.format("%02d", i) + ":" + String.format("%02d", j));
				agendas.saveAndFlush(agenda);
	
			}
		}
		
		/*
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}

		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}

		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());

		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}

		if (usuario.getPessoa().getCodigo() == null) {
			throw new PessoaObrigatoriaException("Obrigatório informar a Pessoa");
		}
		*/
		
		// usuarios.save(usuario);
	}

}
