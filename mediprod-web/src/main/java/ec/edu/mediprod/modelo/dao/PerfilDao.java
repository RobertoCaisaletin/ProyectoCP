package ec.edu.mediprod.modelo.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.mediprod.modelo.entidad.Perfil;

@Local
public interface PerfilDao extends GenericDao<Perfil, Integer> {
	
public List<Perfil> findPerfilByEstado() throws Exception;
 
}
