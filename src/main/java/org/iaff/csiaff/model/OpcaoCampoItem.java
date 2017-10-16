package org.iaff.csiaff.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "opcaocampoitem")
public class OpcaoCampoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	// http://blog.triadworks.com.br/jpa-por-que-voce-deveria-evitar-relacionamento-bidirecional
	@ManyToOne
	@JoinColumn(name = "codigo_tipoitemprontuario")
	private TipoItemProntuario tipoItemProntuario;

	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public TipoItemProntuario getTipoItemProntuario() {
		return tipoItemProntuario;
	}

	public void setTipoItemProntuario(TipoItemProntuario tipoItemProntuario) {
		this.tipoItemProntuario = tipoItemProntuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		OpcaoCampoItem other = (OpcaoCampoItem) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}



}
