package org.iaff.csiaff.repository;

import java.util.List;

import org.iaff.csiaff.model.OpcaoCampoItem;
import org.iaff.csiaff.model.TipoItemProntuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcoesCampoItem extends JpaRepository<OpcaoCampoItem, Long> {

	public List<OpcaoCampoItem> findByTipoItemProntuario(TipoItemProntuario tipoItemProntuario);
	
}
