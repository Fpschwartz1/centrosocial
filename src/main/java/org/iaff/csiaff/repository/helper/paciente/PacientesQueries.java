package org.iaff.csiaff.repository.helper.paciente;

import java.util.List;

import org.iaff.csiaff.model.Paciente;
import org.iaff.csiaff.repository.filter.PacienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacientesQueries {

	public Page<Paciente> filtrar(PacienteFilter filtro, Pageable pageable);

	List<Paciente> filtrarPesquisaRapida(PacienteFilter filtro);
	
}
