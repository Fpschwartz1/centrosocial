package org.iaff.csiaff.repository.filter;

import java.time.LocalDate;

import org.iaff.csiaff.model.Grupo;
import org.iaff.csiaff.model.Usuario;

public class AgendaFilter {

	private Long codigo;
	private Usuario usuario;
	private LocalDate dataAgendamento;
	private String nome;
	private Grupo grupo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getNome() {
		return nome;
	}
	
	public String getDataAgendamentoString () {
		return ""  + String.format("%02d", this.dataAgendamento.getDayOfMonth()) +
		       "/" + String.format("%02d", this.dataAgendamento.getMonthValue()) +
		       "/" + String.format("%02d", this.dataAgendamento.getYear());
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	
}
