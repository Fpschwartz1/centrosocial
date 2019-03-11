package org.iaff.csiaff.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agenda")
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private LocalDate dataAgendamento;
	
	private String horaAgendamento;
	
	// unidirecional
	@OneToOne(optional = true)
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;
	
	// bidirecional
	@ManyToOne(optional = true)
	@JoinColumn(name = "codigo_usuarioprofissional")
	private Usuario usuario;
	
	@Enumerated(EnumType.ORDINAL)
	private SituacaoAtendimento situacaoAtendimento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHoraAgendamento() {
		return horaAgendamento;
	}

	public void setHoraAgendamento(String horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public SituacaoAtendimento getSituacaoAtendimento() {
		return situacaoAtendimento;
	}

	public void setSituacaoAtendimento(SituacaoAtendimento situacaoAtendimento) {
		this.situacaoAtendimento = situacaoAtendimento;
	}

	public String getDataAgendamentoString () {
		return ""  + String.format("%02d", this.dataAgendamento.getDayOfMonth()) +
		       "/" + String.format("%02d", this.dataAgendamento.getMonthValue()) +
		       "/" + String.format("%02d", this.dataAgendamento.getYear());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
