package org.iaff.csiaff.repository;

import java.util.List;

import org.iaff.csiaff.model.Agenda;
import org.iaff.csiaff.model.Usuario;
import org.iaff.csiaff.repository.helper.agenda.AgendasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Agendas extends JpaRepository<Agenda, Long>, AgendasQueries {
	
	public List<Agenda> findByUsuario(Usuario usuario);

}