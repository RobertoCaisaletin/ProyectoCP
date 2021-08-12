package ec.edu.mediprod.modelo.dao.impl;

import javax.ejb.Stateless;

import ec.edu.mediprod.modelo.dao.ProductoDao;
import ec.edu.mediprod.modelo.entidad.Producto;

@Stateless
public class ProductoDaoImpl extends GenericDaoImpl<Producto, Integer>  implements ProductoDao	{

}
