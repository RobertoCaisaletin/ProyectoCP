package ec.edu.mediprod.modelo.dao;

import javax.ejb.Local;

import ec.edu.mediprod.modelo.entidad.Proceso;

@Local
public interface ProcesoDao extends GenericDao<Proceso, Integer> {

}
