package ec.edu.mediprod.controlador;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ec.edu.mediprod.modelo.entidad.Usuario;

@Named(value = "baseViewBean")
@SessionScoped
public class BaseViewBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6116263469392978611L;

	private String accion = "";

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	public static void info(String messageTitle, String messageContent) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, messageTitle, messageContent));
	}

	public static void error(String messageTitle, String messageContent) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, messageTitle, messageContent));
	}

	public static void warn(String messageTitle, String messageContent) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, messageTitle, messageContent));
	}

	public Usuario getUsuarioSesion() {
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userSesion");
	}

	public boolean isUsuarioAdministrador() {
		return getUsuarioSesion() != null && getUsuarioSesion().getPerfil().getDescripcion().equals("ADMINISTRADOR");
	}

	public boolean isUsuarioDigitador() {
		return getUsuarioSesion() != null && getUsuarioSesion().getPerfil().getDescripcion().equals("DIGITADOR");
	}

}
