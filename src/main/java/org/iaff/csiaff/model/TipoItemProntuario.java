package org.iaff.csiaff.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tipoitemprontuario")
public class TipoItemProntuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codigo_grupo") //, nullable=false)
	private Grupo grupo;

	@NotNull(message = "Obrigatório informar o tipo do item")
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private TipoItem tipoItem;

	@NotNull(message = "Obrigatório informar o tipo do campo")
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private TipoCampo tipoCampo;
	
	// indicador de sigilo
	private boolean indicadorSigilo;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "codigo_tipoitemprontuario")
	private List<OpcoesCampoItem> opcoescampoitem;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "codigo_tipoitemprontuario")
	private List<ItemProntuario> itemprontuario;
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public TipoCampo getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(TipoCampo tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public boolean isIndicadorSigilo() {
		return indicadorSigilo;
	}

	public void setIndicadorSigilo(boolean indicadorSigilo) {
		this.indicadorSigilo = indicadorSigilo;
	}

	public List<OpcoesCampoItem> getOpcoescampoitem() {
		return opcoescampoitem;
	}

	public void setOpcoescampoitem(List<OpcoesCampoItem> opcoescampoitem) {
		this.opcoescampoitem = opcoescampoitem;
	}

	public List<ItemProntuario> getItemprontuario() {
		return itemprontuario;
	}

	public void setItemprontuario(List<ItemProntuario> itemprontuario) {
		this.itemprontuario = itemprontuario;
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
		TipoItemProntuario other = (TipoItemProntuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}



}
