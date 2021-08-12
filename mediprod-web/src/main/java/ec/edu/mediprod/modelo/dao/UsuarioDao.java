package ec.edu.mediprod.modelo.dao;

import javax.ejb.Local;

import ec.edu.mediprod.modelo.entidad.Usuario;

@Local
public interface UsuarioDao extends GenericDao<Usuario, Integer> {

	Usuario findUserByUsernameAndPass(Usuario usuario) throws Exception;

}
