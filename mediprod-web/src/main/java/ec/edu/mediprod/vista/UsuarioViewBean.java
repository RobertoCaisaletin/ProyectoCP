package ec.edu.mediprod.vista;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.mediprod.controlador.PageViewBean;
import ec.edu.mediprod.modelo.dao.PerfilDao;
import ec.edu.mediprod.modelo.dao.UsuarioDao;
import ec.edu.mediprod.modelo.entidad.Perfil;
import ec.edu.mediprod.modelo.entidad.Usuario;

@ViewScoped
@Named("usuarioViewBean")
public class UsuarioViewBean extends PageViewBean implements Serializable {

	private static final long serialVersionUID = -3193543755477832584L;
	private final static Logger LOGGER = Logger.getLogger(UsuarioViewBean.class.getName());

	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private PerfilDao perfilDao;
	private List<Usuario> listUsuario;
	private List<Perfil> listPerfil;
	private Usuario usuarioSelected;
	private Integer idPerfilSelected;
	private boolean statusUsuario;

	public UsuarioViewBean() {
		super();
	}

	/**
	 * @return the listUsuario
	 */
	public List<Usuario> getListUsuario() {
		return listUsuario;
	}

	/**
	 * @param listUsuario the listUsuario to set
	 */
	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}

	/**
	 * @return the usuarioSelected
	 */
	public Usuario getUsuarioSelected() {
		return usuarioSelected;
	}

	/**
	 * @param usuarioSelected the usuarioSelected to set
	 */
	public void setUsuarioSelected(Usuario usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}

	/**
	 * @return the listPerfil
	 */
	public List<Perfil> getListPerfil() {
		return listPerfil;
	}

	/**
	 * @param listPerfil the listPerfil to set
	 */
	public void setListPerfil(List<Perfil> listPerfil) {
		this.listPerfil = listPerfil;
	}

	/**
	 * @return the idPerfilSelected
	 */
	public Integer getIdPerfilSelected() {
		return idPerfilSelected;
	}

	/**
	 * @param idPerfilSelected the idPerfilSelected to set
	 */
	public void setIdPerfilSelected(Integer idPerfilSelected) {
		this.idPerfilSelected = idPerfilSelected;
	}
	
	

	/**
	 * @return the statusUsuario
	 */
	public boolean isStatusUsuario() {
		return statusUsuario;
	}

	/**
	 * @param statusUsuario the statusUsuario to set
	 */
	public void setStatusUsuario(boolean statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	@PostConstruct
	public void init() {
		try {
			LOGGER.info("Obteniendo Información del Usuario");
			obtenerListUsuario();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("USUARIO", "Error del Sistema.");
		}

	}

	private void obtenerListUsuario() throws Exception {
		LOGGER.info("Inicializando Lista del Usuario");
		listUsuario = usuarioDao.findAll();
		listPerfil = perfilDao.findAll();
	}

	@Override
	public void inicializar() throws Exception {
		LOGGER.info("Inicializando USUARIO");
		setAccion("C");
		usuarioSelected = new Usuario();
		idPerfilSelected= null;
		statusUsuario = true;
	}

	@Override
	public void editar(ActionEvent ev) throws Exception {
		setAccion("U");
		idPerfilSelected = usuarioSelected != null ? usuarioSelected.getPerfil().getIdPerfil() : null;
		statusUsuario = usuarioSelected != null  && usuarioSelected.getEstado().equals("HABILITADO");
	}

	@Override
	public void guardarOrModificar(ActionEvent ev) {
		try {
			usuarioSelected.setEstado(statusUsuario ? "ACTIVO" : "INACTIVO");
			usuarioSelected.setPerfil(new Perfil(idPerfilSelected));
			if (getAccion().equals("C")) {
				LOGGER.info("Guardando Información del USUARIO");
				usuarioDao.guardar(usuarioSelected);
			} else if (getAccion().equals("U")) {
				LOGGER.info("Modificando Información del USUARIO");
				usuarioDao.actualizar(usuarioSelected);

			}
			info("USUARIO", getAccion().equals("C") ? "Guardado exitosamente." : "Actualizado exitosamente.");
			obtenerListUsuario();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("USUARIO", "Error del Sistema.");
		}

	}

	@Override
	public void actualizar(ActionEvent ev) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(ActionEvent ev) {
		try {
			LOGGER.info("Eliminando Información del USUARIO");
			usuarioDao.eliminar(usuarioSelected);
			info("USUARIO", "Eliminado exitosamente.");
			obtenerListUsuario();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("USUARIO", "Error del Sistema.");
		}

	}

	@Override
	public void buscar(ActionEvent ev) throws Exception {
		// TODO Auto-generated method stub

	}

}
