package org.iaff.csiaff.repository.filter;

import java.util.List;

import org.iaff.csiaff.model.Grupo;
import org.iaff.csiaff.model.TipoItem;

public class ItemProntuarioFilter {

	private Long codigoPaciente;
	private String nomePaciente;
	private List<Grupo> grupos;
	private List<TipoItem> tiposItem;

	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<TipoItem> getTiposItem() {
		return tiposItem;
	}

	public void setTiposItem(List<TipoItem> tiposItem) {
		this.tiposItem = tiposItem;
	}

}
