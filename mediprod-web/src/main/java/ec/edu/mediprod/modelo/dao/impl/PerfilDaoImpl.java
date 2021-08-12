package ec.edu.mediprod.modelo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import ec.edu.mediprod.modelo.dao.PerfilDao;
import ec.edu.mediprod.modelo.entidad.Perfil;

@Stateless
public class PerfilDaoImpl extends GenericDaoImpl<Perfil, Integer>  implements PerfilDao	{

	@Override
	public List<Perfil> findPerfilByEstado() throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("estado","HABILITADO");
		return findByNamedQuery("Perfil.findByEstado", parameter);
	}

}
