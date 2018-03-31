package org.iaff.csiaff.repository;

import java.util.List;

import org.iaff.csiaff.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Grupos extends JpaRepository<Grupo, Long> {

	List<Grupo> findByAnamnese(boolean anamnese);
	
	List<Grupo> findByRegistroprofissionalNotNull();
}
