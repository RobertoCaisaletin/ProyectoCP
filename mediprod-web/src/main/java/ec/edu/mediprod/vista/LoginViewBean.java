package ec.edu.mediprod.vista;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.mediprod.modelo.dao.UsuarioDao;
import ec.edu.mediprod.modelo.entidad.Usuario;

@ViewScoped
@Named("loginViewBean")
public class LoginViewBean implements Serializable {

	private static final long serialVersionUID = -3827822654425613362L;

	private final static Logger LOGGER = Logger.getLogger(LoginViewBean.class.getName());

	@Inject
	private UsuarioDao usuarioDao;
	private String usuario;
	private String password;

	public LoginViewBean() {
		super();
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String autentificarUsuario() {
		LOGGER.info("AUTENTIFICANDO USUARIO");
		String path = null;
		try {
			Usuario userSesion = usuarioDao.findUserByUsernameAndPass(new Usuario(password, usuario));
			if (userSesion != null) {
				path = "portal/home?faces-redirect=true";
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userSesion", userSesion);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"LOGIN", "Las credenciales ingresadas no es la correcta."));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "LOGIN", "Error"));
		}
		return path;
	}

}
