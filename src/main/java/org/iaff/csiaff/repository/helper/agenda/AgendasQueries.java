package org.iaff.csiaff.repository.helper.agenda;

import org.iaff.csiaff.model.Agenda;
import org.iaff.csiaff.repository.filter.AgendaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgendasQueries {

	public Page<Agenda> filtrar(AgendaFilter filtro, Pageable pageable);
	
}
