package org.iaff.csiaff.repository;

import java.util.List;

import org.iaff.csiaff.model.Grupo;
import org.iaff.csiaff.model.TipoItem;
import org.iaff.csiaff.model.TipoItemProntuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiposItensProntuario extends JpaRepository<TipoItemProntuario, Long> {

	public List<TipoItemProntuario> findByGrupoAndTipoItem(Grupo grupo, TipoItem tipoItem);
	
	public List<TipoItemProntuario> findByTipoItem(TipoItem tipoItem);
}
