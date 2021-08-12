package ec.edu.mediprod.vista;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import ec.edu.mediprod.controlador.PageViewBean;
import ec.edu.mediprod.modelo.dao.ProcesoDao;
import ec.edu.mediprod.modelo.dao.ProductoDao;
import ec.edu.mediprod.modelo.dao.RendimientoDao;
import ec.edu.mediprod.modelo.entidad.Proceso;
import ec.edu.mediprod.modelo.entidad.Producto;
import ec.edu.mediprod.modelo.entidad.Rendimiento;

@ViewScoped
@Named("procesoViewBean")
public class ProcesoViewBean extends PageViewBean implements Serializable {

	private static final long serialVersionUID = -8809953180580339882L;

	private final static Logger LOGGER = Logger.getLogger(ProcesoViewBean.class.getName());

	@Inject
	private ProcesoDao procesoDao;
	@Inject
	private ProductoDao productoDao;
	@Inject
	private RendimientoDao rendimientoDao;
	private List<Proceso> listProceso;
	private List<Producto> listProducto;
	private List<Rendimiento> listRendimiento;
	private Proceso procesoSelected;
	private Integer idProductoSelected;
	private Rendimiento rendimientoSelected;

	public ProcesoViewBean() {
		super();
	}

	/**
	 * @return the listProceso
	 */
	public List<Proceso> getListProceso() {
		return listProceso;
	}

	/**
	 * @param listProceso the listProceso to set
	 */
	public void setListProceso(List<Proceso> listProceso) {
		this.listProceso = listProceso;
	}

	/**
	 * @return the procesoSelected
	 */
	public Proceso getProcesoSelected() {
		return procesoSelected;
	}

	/**
	 * @param procesoSelected the procesoSelected to set
	 */
	public void setProcesoSelected(Proceso procesoSelected) {
		this.procesoSelected = procesoSelected;
	}

	/**
	 * @return the listProducto
	 */
	public List<Producto> getListProducto() {
		return listProducto;
	}

	/**
	 * @param listProducto the listProducto to set
	 */
	public void setListProducto(List<Producto> listProducto) {
		this.listProducto = listProducto;
	}

	/**
	 * @return the idProductoSelected
	 */
	public Integer getIdProductoSelected() {
		return idProductoSelected;
	}

	/**
	 * @param idProductoSelected the idProductoSelected to set
	 */
	public void setIdProductoSelected(Integer idProductoSelected) {
		this.idProductoSelected = idProductoSelected;
	}

	/**
	 * @return the listRendimiento
	 */
	public List<Rendimiento> getListRendimiento() {
		return listRendimiento;
	}

	/**
	 * @param listRendimiento the listRendimiento to set
	 */
	public void setListRendimiento(List<Rendimiento> listRendimiento) {
		this.listRendimiento = listRendimiento;
	}

	/**
	 * @return the rendimientoSelected
	 */
	public Rendimiento getRendimientoSelected() {
		return rendimientoSelected;
	}

	/**
	 * @param rendimientoSelected the rendimientoSelected to set
	 */
	public void setRendimientoSelected(Rendimiento rendimientoSelected) {
		this.rendimientoSelected = rendimientoSelected;
	}

	@PostConstruct
	public void init() {
		try {
			LOGGER.info("Obteniendo Información del Proceso");
			obtenerListProducto();
			obtenerListProceso();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("Proceso", "Error del Sistema.");
		}

	}

	private void obtenerListProceso() throws Exception {
		LOGGER.info("Inicializando Lista del Proceso");
		listProceso = procesoDao.findAll();
	}

	private void obtenerListProducto() throws Exception {
		LOGGER.info("Inicializando Lista del Proceso");
		listProducto = productoDao.findAll();
	}

	@Override
	public void inicializar() throws Exception {
		LOGGER.info("Inicializando Proceso");
		setAccion("C");
		procesoSelected = new Proceso();
		procesoSelected.setEstado("INGRESADO");
		idProductoSelected = null;
	}

	@Override
	public void editar(ActionEvent ev) throws Exception {

	}

	public void editarProceso(Proceso proceso) throws Exception {
		setAccion("U");
		procesoSelected = proceso;
		idProductoSelected = procesoSelected.getProducto().getIdProducto();

	}

	@Override
	public void guardarOrModificar(ActionEvent ev) {
		try {
			procesoSelected.setProducto(new Producto(idProductoSelected));
			if (getAccion().equals("C")) {
				LOGGER.info("Guardando Información del Proceso");
				procesoSelected.setFechaRegistro(new Date());
				procesoSelected.setEstado("INGRESADO");
				procesoDao.guardar(procesoSelected);
			} else if (getAccion().equals("U")) {
				LOGGER.info("Modificando Información del Proceso");
				procesoDao.actualizar(procesoSelected);
			}
			info("Proceso", getAccion().equals("C") ? "Guardado exitosamente." : "Actualizado exitosamente.");
			obtenerListProceso();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("Proceso", "Error del Sistema.");
		}

	}

	@Override
	public void actualizar(ActionEvent ev) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(ActionEvent ev) {
		try {
			LOGGER.info("Eliminando Información del Proceso");
			procesoDao.eliminar(procesoSelected);
			info("Proceso", "Eliminado exitosamente.");
			obtenerListProceso();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("Proceso", "Error del Sistema.");
		}

	}

	@Override
	public void buscar(ActionEvent ev) throws Exception {
		// TODO Auto-generated method stub

	}

	public void mostrarRendimiento(Proceso proceso) throws Exception {
		LOGGER.info("Mostrando Lista de Rendimiento");
		setAccion("C");
		procesoSelected = proceso;
		if (isUsuarioAdministrador()) {
			listRendimiento = rendimientoDao.findRendimientoByProceso(procesoSelected);
		} else if (isUsuarioDigitador()) {
			listRendimiento = rendimientoDao.findRendimientoByProcesoAndUsuario(procesoSelected, getUsuarioSesion());
		}
	}

	public void agregarRendimiento() throws Exception {
		LOGGER.info("Agregando Rendimiento");
		rendimientoSelected = new Rendimiento();
	}

	public void guardarOrModificarRendimiento(ActionEvent ev) {
		try {
			boolean actualizarProceso = listRendimiento !=null && listRendimiento.size()>0;
			if(!actualizarProceso) {
			procesoSelected.setFechaInicio(new Date());
			procesoSelected.setEstado("PRODUCCION");
			}
			rendimientoSelected.setUsuario(getUsuarioSesion());
			rendimientoSelected.setProceso(procesoSelected);
			rendimientoSelected.setHora(new Date());
			rendimientoDao.procesarProduccionAndRendimiento(procesoSelected, rendimientoSelected, actualizarProceso);
			mostrarRendimiento(procesoSelected);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("Rendimiento", "Error del Sistema.");
		}

	}
	
	public void modificarRendimiento(ActionEvent ev) {
		try {			
			rendimientoDao.actualizar(rendimientoSelected);
			mostrarRendimiento(procesoSelected);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("Rendimiento", "Error del Sistema.");
		}

	}

	public void aprobarProduccion(ActionEvent ev) {
		try {
			LOGGER.info("Aprobar PRODUCCION");
			procesoSelected.setEstado("APROBADO");
			procesoDao.actualizar(procesoSelected);
			info("PROCESO", "Aprobado exitosamente.");
			obtenerListProceso();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("PROCESO", "Error del Sistema.");
		}

	}

	public void finalizarProceso(ActionEvent ev) {
		try {
			LOGGER.info("Finalizar Proceso");
			procesoSelected.setFechaFinal(new Date());
			procesoSelected.setEstado("FINALIZADO");
			procesoDao.actualizar(procesoSelected);
			info("Proceso", "Finalizado exitosamente.");
			obtenerListProceso();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("Proceso", "Error del Sistema.");
		}

	}
	
	public void revisarProceso(ActionEvent ev) {
		try {
			LOGGER.info("Revisar Proceso");
			procesoSelected.setEstado("REVISION");
			procesoDao.actualizar(procesoSelected);
			info("Proceso", "Proceso en Revisión.");
			obtenerListProceso();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("Proceso", "Error del Sistema.");
		}

	}
	
	public void onRowEdit(RowEditEvent<Rendimiento> event) {
		try {
			rendimientoDao.actualizar(event.getObject());
			mostrarRendimiento(procesoSelected);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("Rendimiento", "Error del Sistema.");
		}
		
    }

}
