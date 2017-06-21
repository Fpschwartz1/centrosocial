package org.iaff.csiaff.repository;

import java.util.List;

import org.iaff.csiaff.model.ItemProntuario;
import org.iaff.csiaff.model.Paciente;
import org.iaff.csiaff.repository.helper.itemprontuario.ItensProntuarioQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensProntuario extends JpaRepository<ItemProntuario, Long>, ItensProntuarioQueries {

	public List<ItemProntuario> findByPaciente(Paciente paciente);
	
}
