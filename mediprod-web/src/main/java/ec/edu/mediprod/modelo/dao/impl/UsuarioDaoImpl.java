package ec.edu.mediprod.modelo.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import ec.edu.mediprod.modelo.dao.UsuarioDao;
import ec.edu.mediprod.modelo.entidad.Usuario;

@Stateless
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer>  implements UsuarioDao	{

	@Override
	public Usuario findUserByUsernameAndPass(Usuario usuario) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("username",usuario.getUsername());
		parameter.put("password",usuario.getPassword());
		return findByNamedQuery("Usuario.findUserByUsernameAndPass", parameter).get(0);		
	}

}
