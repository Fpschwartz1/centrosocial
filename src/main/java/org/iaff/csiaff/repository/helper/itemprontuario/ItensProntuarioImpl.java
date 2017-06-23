package org.iaff.csiaff.repository.helper.itemprontuario;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.iaff.csiaff.model.Grupo;
import org.iaff.csiaff.model.ItemProntuario;
import org.iaff.csiaff.model.TipoItemProntuario;
import org.iaff.csiaff.repository.filter.ItemProntuarioFilter;
import org.iaff.csiaff.repository.paginacao.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public class ItensProntuarioImpl implements ItensProntuarioQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<ItemProntuario> filtrar(ItemProntuarioFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ItemProntuario.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		List<ItemProntuario> filtrados = criteria.list();
		filtrados.forEach(u -> Hibernate.initialize(u.getTipoitemprontuario().getGrupo()));
		return new PageImpl<>(filtrados, pageable, total(filtro));
	}
	
	
	private Long total(ItemProntuarioFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ItemProntuario.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	

	private void adicionarFiltro(ItemProntuarioFilter filtro, Criteria criteria) {
		if (filtro != null) {
			boolean tipoA = false;
			int intA = 0;
			
			if (!StringUtils.isEmpty(filtro.getCodigoPaciente())) {
				criteria.add(Restrictions.eq("paciente.codigo", filtro.getCodigoPaciente()));
			}

			// http://www.devmedia.com.br/hibernate-api-criteria-realizando-consultas/29627
			if (filtro.getTiposItem() != null && !filtro.getTiposItem().isEmpty()) {
				
				List<Criterion> subqueries = new ArrayList<>();
				for(int i=0; i< filtro.getTiposItem().size(); i++){
					
					// o objeto TipoItemProntuario é a ponte 
					DetachedCriteria dc = DetachedCriteria.forClass(TipoItemProntuario.class);
					// a comparação se dá no atributo grupo de TipoItemProntuario
					dc.add(Restrictions.eq("tipoItem", filtro.getTiposItem().get(i)));
					// se anamnese for selecionada, setar o flag
					if(filtro.getTiposItem().get(i).name() == "A") { tipoA = true; intA = i; }					 
					// a coluna resultante da execução do critério é o atributo codigo de TipoItemProntuario 
					dc.setProjection(Projections.property("codigo"));
					
					// atributo codigo de TipoItemProntuario será comparado com
					// tipoitemprontuario.codigo de ItemProntuario
					subqueries.add(Subqueries.propertyIn("tipoitemprontuario.codigo", dc));

				}

				Criterion[] criterions = new Criterion[subqueries.size()];
				// aplica operador OU para os grupos
				criteria.add(Restrictions.or(subqueries.toArray(criterions)));
			}
			
			// https://docs.jboss.org/hibernate/orm/3.5/reference/pt-BR/html/querycriteria.html#querycriteria-projection
			if (filtro.getGrupos() != null && !filtro.getGrupos().isEmpty()) {
				List<Criterion> subqueries = new ArrayList<>();
				for (Long codigoGrupo : filtro.getGrupos().stream().mapToLong(Grupo::getCodigo).toArray()) {
					// o teste é no objeto Grupo de TipoItemProntuario de ItemProntuario
					
					// o objeto TipoItemProntuario é a ponte 
					DetachedCriteria dc = DetachedCriteria.forClass(TipoItemProntuario.class);
					// a comparação se dá no atributo grupo de TipoItemProntuario
					dc.add(Restrictions.eq("grupo.codigo", codigoGrupo));
					// a coluna resultante da execução do critério é o atributo codigo de TipoItemProntuario 
					dc.setProjection(Projections.property("codigo"));
					
					// atributo codigo de TipoItemProntuario será comparado com
					// tipoitemprontuario.codigo de ItemProntuario
					subqueries.add(Subqueries.propertyIn("tipoitemprontuario.codigo", dc));
				}

				//quando anamnese for incluida, incluir na subquery de grupo
				if(tipoA){
					DetachedCriteria dc = DetachedCriteria.forClass(TipoItemProntuario.class);
					dc.add(Restrictions.eq("tipoItem", filtro.getTiposItem().get(intA)));
					dc.setProjection(Projections.property("codigo"));
					subqueries.add(Subqueries.propertyIn("tipoitemprontuario.codigo", dc));
				}
				
				Criterion[] criterions = new Criterion[subqueries.size()];
				// aplica operador OU para os grupos
				criteria.add(Restrictions.or(subqueries.toArray(criterions)));
			}
		}
	}

}

// select count(*) as y0_ from itemprontuario this_ where this_.codigo_paciente=? and (this_.codigo_tipoitemprontuario in (select this_.codigo as y0_ from tipoitemprontuario this_ where this_.codigo_grupo=?)) and(OR) (this_.codigo_tipoitemprontuario in (select this_.codigo as y0_ from tipoitemprontuario this_ where this_.codigo_grupo=? and this_.tipo_item=?))
