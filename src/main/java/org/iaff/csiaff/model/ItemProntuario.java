package org.iaff.csiaff.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "itemprontuario")
public class ItemProntuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	// bidirecional
	@ManyToOne(optional = false)
	@JoinColumn(name="codigo_paciente")
	private Paciente paciente;
	
	// unidirecional
	@OneToOne
	@JoinColumn(name = "codigo_usuarioprofissional")
	private Usuario usuario;

	private String nomeGrupo;
	
	@NotNull(message = "Obrigatório informar o tipo do item")
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private TipoItem tipoItem;
	
	// unidirecional
	@NotNull(message = "Categoria é obrigatória")
	@ManyToOne
	@JoinColumn(name="codigo_tipoitemprontuario", nullable=false)
	private TipoItemProntuario tipoitemprontuario;
	
	@NotBlank(message = "Conteúdo é obrigatório")
	private String Valor;
	
	private LocalDateTime dataLancamento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public TipoItemProntuario getTipoitemprontuario() {
		return tipoitemprontuario;
	}

	public void setTipoitemprontuario(TipoItemProntuario tipoitemprontuario) {
		this.tipoitemprontuario = tipoitemprontuario;
	}
	
	public String getValor() {
		return Valor;
	}

	public void setValor(String valor) {
		Valor = valor;
	}

	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public String getDataLancamentoString () {
		return ""     + String.format("%02d", this.dataLancamento.getDayOfMonth()) +
				"/"   + String.format("%02d", this.dataLancamento.getMonthValue()) +
				"/"   + String.format("%02d", this.dataLancamento.getYear()) +
				" - " + String.format("%02d", this.dataLancamento.getHour()) +
				":"   + String.format("%02d", this.dataLancamento.getMinute()) +
				":"   + String.format("%02d", this.dataLancamento.getSecond());
	}
	
	/*
	public String getGrupoNome() {
		if(getTipoitemprontuario().getGrupo() == null){
			return "Anamnese";
		}
		return getTipoitemprontuario().getGrupo().getNome();
	}
	*/

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
		ItemProntuario other = (ItemProntuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
