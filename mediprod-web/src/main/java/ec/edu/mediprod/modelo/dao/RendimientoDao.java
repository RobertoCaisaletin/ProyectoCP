package ec.edu.mediprod.modelo.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.mediprod.modelo.entidad.Proceso;
import ec.edu.mediprod.modelo.entidad.Rendimiento;
import ec.edu.mediprod.modelo.entidad.Usuario;

@Local
public interface RendimientoDao extends GenericDao<Rendimiento, Integer> {
	
	public List<Rendimiento> findRendimientoByProceso(Proceso proceso) throws Exception;
	
	public List<Rendimiento> findRendimientoByProcesoAndUsuario(Proceso proceso, Usuario usuario) throws Exception;
	
	public void procesarProduccionAndRendimiento(Proceso procesoSelected, Rendimiento rendimientoSelected, boolean actualizarProceso)throws Exception;

}
