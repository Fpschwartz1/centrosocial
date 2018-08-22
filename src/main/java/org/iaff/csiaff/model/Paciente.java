package org.iaff.csiaff.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "paciente")
@DynamicUpdate
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@OneToOne(optional = false)
	@JoinColumn(name = "codigo_pessoa", nullable=false)
	private Pessoa pessoa;
	
	private String foneAdicional;
	
	private String foneRecado;
	
	private String foneEmergencia;
	
	// bidirecional
	@JsonIgnore
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemProntuario> itensprontuario;
	
	// unidirecional
	/*
	@OneToMany
	@JoinColumn(name = "codigo_paciente", nullable=false)
	private List<ItemProntuario> itensprontuario;
	*/
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}	
	
	public String getFoneAdicional() {
		return foneAdicional;
	}

	public void setFoneAdicional(String foneAdicional) {
		this.foneAdicional = foneAdicional;
	}

	public String getFoneRecado() {
		return foneRecado;
	}

	public void setFoneRecado(String foneRecado) {
		this.foneRecado = foneRecado;
	}

	public String getFoneEmergencia() {
		return foneEmergencia;
	}

	public void setFoneEmergencia(String foneEmergencia) {
		this.foneEmergencia = foneEmergencia;
	}
	
	public boolean isNovo() {
		return codigo == null;
	}

	public List<ItemProntuario> getItensprontuario() {
		return itensprontuario;
	}

	public void setItensprontuario(List<ItemProntuario> itensprontuario) {
		this.itensprontuario = itensprontuario;
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
		Paciente other = (Paciente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
