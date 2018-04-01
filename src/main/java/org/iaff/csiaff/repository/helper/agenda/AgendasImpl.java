package org.iaff.csiaff.repository.helper.agenda;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.iaff.csiaff.model.Agenda;
import org.iaff.csiaff.model.Usuario;
import org.iaff.csiaff.repository.Agendas;
import org.iaff.csiaff.repository.Usuarios;
import org.iaff.csiaff.repository.filter.AgendaFilter;
import org.iaff.csiaff.repository.paginacao.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public class AgendasImpl implements AgendasQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@Autowired
	private Agendas agendas;
	
	@Autowired
	private Usuarios usuarios;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Agenda> filtrar(AgendaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Agenda.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
	
		@SuppressWarnings("unused")
		List<Agenda> lista = criteria.list();
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(AgendaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Agenda.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(AgendaFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (filtro.getCodigo() != null) {
				Disjunction ou = Restrictions.disjunction();
				ou.add(Restrictions.eq("codigo", -1L)); // quando não há pacientes
				for(Long codigoAgenda : agendas.findByUsuario(usuarios.findOne(filtro.getCodigo())).stream().mapToLong(Agenda::getCodigo).toArray()){
					ou.add(Restrictions.eq("codigo", codigoAgenda));
				}
				criteria.add(ou);
			}
			if(filtro.getDataAgendamento() != null){
				criteria.add(Restrictions.eq("dataAgendamento", filtro.getDataAgendamento()));
			}
			if(filtro.getGrupos() != null && !filtro.getGrupos().isEmpty()){
				List<Usuario> nusuarios = usuarios.usuariosDoGrupoCujoNome(filtro.getGrupos().get(0), filtro.getNome());
				if(!nusuarios.isEmpty()){
					Disjunction ou = Restrictions.disjunction();
					nusuarios.forEach(u -> ou.add(Restrictions.eq("usuario", u)));
					criteria.add(ou);
				} else{
					criteria.add(Restrictions.eq("usuario", null));
				}
			}
			criteria.addOrder(Order.asc("usuario"));
			criteria.addOrder(Order.asc("horaAgendamento"));
		}
	}

}
