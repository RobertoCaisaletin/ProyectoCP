package ec.edu.mediprod.modelo.dao;

import javax.ejb.Local;

import ec.edu.mediprod.modelo.entidad.Producto;

@Local
public interface ProductoDao extends GenericDao<Producto, Integer> {

}
