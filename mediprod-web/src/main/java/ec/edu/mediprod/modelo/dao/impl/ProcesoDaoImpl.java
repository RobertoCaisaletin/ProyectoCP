package ec.edu.mediprod.modelo.dao.impl;

import javax.ejb.Stateless;

import ec.edu.mediprod.modelo.dao.ProcesoDao;
import ec.edu.mediprod.modelo.entidad.Proceso;

@Stateless
public class ProcesoDaoImpl extends GenericDaoImpl<Proceso, Integer>  implements ProcesoDao	{

}
