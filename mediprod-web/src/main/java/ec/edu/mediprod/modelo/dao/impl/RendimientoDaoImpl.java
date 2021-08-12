package ec.edu.mediprod.modelo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.mediprod.modelo.dao.ProcesoDao;
import ec.edu.mediprod.modelo.dao.RendimientoDao;
import ec.edu.mediprod.modelo.entidad.Proceso;
import ec.edu.mediprod.modelo.entidad.Rendimiento;
import ec.edu.mediprod.modelo.entidad.Usuario;

@Stateless
public class RendimientoDaoImpl extends GenericDaoImpl<Rendimiento, Integer> implements RendimientoDao {

	@Inject
	private ProcesoDao procesoDao;

	@Override
	public List<Rendimiento> findRendimientoByProceso(Proceso proceso) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("proceso", proceso);
		return findByNamedQuery("Rendimiento.findByProceso", parameter);
	}

	@Override
	public List<Rendimiento> findRendimientoByProcesoAndUsuario(Proceso proceso, Usuario usuario) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("proceso", proceso);
		parameter.put("usuario", usuario);
		return findByNamedQuery("Rendimiento.findByProcesoAndUsuario", parameter);
	}

	@Override
	public void procesarProduccionAndRendimiento(Proceso procesoSelected, Rendimiento rendimientoSelected, boolean actualizarProceso) throws Exception {
		if (!actualizarProceso) {
			procesoDao.actualizar(procesoSelected);
		}
		super.guardar(rendimientoSelected);
	}

}
