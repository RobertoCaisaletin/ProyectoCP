package ec.edu.mediprod.modelo.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.mediprod.modelo.entidad.Menu;
import ec.edu.mediprod.modelo.entidad.Usuario;

@Local
public interface MenuDao extends GenericDao<Menu, Integer> {

	public List<Menu> findMenuByPerfilAndUsuario(Usuario usuario) throws Exception;
}
