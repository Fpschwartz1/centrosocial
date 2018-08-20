package org.iaff.csiaff.repository.helper.paciente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.iaff.csiaff.model.Paciente;
import org.iaff.csiaff.model.Pessoa;
import org.iaff.csiaff.repository.Pessoas;
import org.iaff.csiaff.repository.filter.PacienteFilter;
import org.iaff.csiaff.repository.paginacao.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


public class PacientesImpl implements PacientesQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@Autowired
	private Pessoas pessoas;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Paciente> filtrar(PacienteFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Paciente.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
				
	
		// @SuppressWarnings("unused")
		// List<Paciente> lista = criteria.list();
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(PacienteFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Paciente.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(PacienteFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				Disjunction ou = Restrictions.disjunction();
				for(Long codigoPessoa : pessoas.findByNomeStartingWithIgnoreCase(filtro.getNome()).stream().mapToLong(Pessoa::getCodigo).toArray()){
					ou.add(Restrictions.eq("pessoa.codigo", codigoPessoa));
				}
				if(ou.conditions().iterator().hasNext()){
					criteria.add(ou);	
				} else{ // caso não tenha encontrado 
					criteria.add(Restrictions.eq("pessoa.codigo", -1L));
				}
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Paciente> filtrarPesquisaRapida(PacienteFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Paciente.class);
		
		adicionarFiltro(filtro, criteria);
	
		return criteria.list();
	}
	

}
