package ec.edu.mediprod.modelo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import ec.edu.mediprod.modelo.dao.MenuDao;
import ec.edu.mediprod.modelo.entidad.Menu;
import ec.edu.mediprod.modelo.entidad.Usuario;

@Stateless
public class MenuDaoImpl extends GenericDaoImpl<Menu, Integer>  implements MenuDao	{

	@Override
	public List<Menu> findMenuByPerfilAndUsuario(Usuario usuario) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("perfil",usuario.getPerfil());
		return findByNamedQuery("Menu.findByPerfilAndUsuario", parameter);
	}

}
