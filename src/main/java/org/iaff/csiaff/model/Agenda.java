package org.iaff.csiaff.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agenda")
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private LocalDateTime dataAgendamento;
	
	// unidirecional
	@OneToOne(optional = false)
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;
	
	// unidirecional
	@OneToOne(optional = false)
	@JoinColumn(name = "codigo_usuarioprofissional")
	private Usuario usuario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
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

	public String getDataAgendamentoString () {
		return ""    + String.format("%02d", this.dataAgendamento.getDayOfMonth()) +
		       "/"   + String.format("%02d", this.dataAgendamento.getMonthValue()) +
		       "/"   + String.format("%02d", this.dataAgendamento.getYear())+
			   " - " + String.format("%02d", this.dataAgendamento.getHour()) +
			   ":"   + String.format("%02d", this.dataAgendamento.getMinute()) +
			   ":"   + String.format("%02d", this.dataAgendamento.getSecond()); 
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
