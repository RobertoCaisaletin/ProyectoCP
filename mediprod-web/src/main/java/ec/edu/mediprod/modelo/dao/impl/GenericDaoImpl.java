package ec.edu.mediprod.modelo.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.mediprod.modelo.dao.GenericDao;

public abstract class GenericDaoImpl<Entity, K extends Serializable> implements GenericDao<Entity, K> {

	private Class<Entity> entityClass;

	private final static Logger LOGGER = Logger.getLogger(GenericDaoImpl.class.getName());

	@Resource
	protected SessionContext sessionContext;
	@PersistenceContext
	protected EntityManager entityManager;

	static final String QUERY_SELECT = "SELECT e FROM ";

	public GenericDaoImpl() {
		Type genericSuperClass = getClass().getGenericSuperclass();
		ParameterizedType parametrizedType = null;
		while (parametrizedType == null) {
			if (genericSuperClass instanceof ParameterizedType) {
				parametrizedType = (ParameterizedType) genericSuperClass;
			} else {
				genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
			}
		}
		entityClass = (Class<Entity>) parametrizedType.getActualTypeArguments()[0];
	}

	@Override
	public void guardar(Entity t) throws Exception {
		try {
			LOGGER.info("Guardando Informarción");
			entityManager.persist(t);
			entityManager.flush();
		} catch (Exception ex) {
			LOGGER.severe("<<< ERROR >>>" + ex.getMessage());
		}

	}

	@Override
	public void actualizar(Entity t) throws Exception {
		try {
			LOGGER.info("Modificando Informarción");
			entityManager.merge(t);
			entityManager.flush();
		} catch (Exception ex) {
			LOGGER.severe("<<< ERROR >>>" + ex.getMessage());
		}

	}

	@Override
	public Entity buscar(K id) throws Exception {
		return entityManager.find(entityClass, id);
	}

	@Override
	public void eliminar(Entity t) throws Exception {
		try {
			LOGGER.info("Eliminando Informarción");
			entityManager.remove(entityManager.merge(t));
			entityManager.flush();
		} catch (Exception ex) {
			LOGGER.severe("<<< ERROR >>>" + ex.getMessage());
		}

	}

	@Override
	public List<Entity> findAll() throws Exception {
		return entityManager.createQuery(getStatement(QUERY_SELECT)).getResultList();
	}

	private String getStatement(String statement) {
		StringBuilder stmt = new StringBuilder(statement);
		stmt.append(entityClass.getSimpleName());
		stmt.append(" e ");
		return stmt.toString();
	}

	public List<Entity> findByNamedQuery(String jpqlQuery, Map<String, Object> parameter) {
		Query query = entityManager.createNamedQuery(jpqlQuery, entityClass);
		setParameter(parameter, query);
		return query.getResultList() != null && query.getResultList().isEmpty() ? null : query.getResultList();
	}

	private void setParameter(Map<String, Object> parameter, Query query) {
		if (parameter != null) {
			for (Map.Entry<String, Object> dto : parameter.entrySet()) {
				query.setParameter(dto.getKey(), dto.getValue());
			}
		}
	}

}
