package ec.edu.mediprod.modelo.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<Entity, PK extends Serializable> {
	
	void guardar(Entity t) throws Exception;

	void actualizar(Entity t) throws Exception;

	Entity buscar(PK id) throws Exception;

	void eliminar(Entity t) throws Exception;
	
	List<Entity> findAll() throws Exception;
}
