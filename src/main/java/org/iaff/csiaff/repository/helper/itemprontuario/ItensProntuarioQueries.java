package org.iaff.csiaff.repository.helper.itemprontuario;

import org.iaff.csiaff.model.ItemProntuario;
import org.iaff.csiaff.repository.filter.ItemProntuarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItensProntuarioQueries {

	public Page<ItemProntuario> filtrar(ItemProntuarioFilter filtro, Pageable pageable);
	
}
