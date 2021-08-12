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
import ec.edu.mediprod.modelo.dao.ProductoDao;
import ec.edu.mediprod.modelo.entidad.Producto;

@ViewScoped
@Named("productoViewBean")
public class ProductoViewBean extends PageViewBean implements Serializable {

	private static final long serialVersionUID = -3193543755477832584L;
	private final static Logger LOGGER = Logger.getLogger(ProductoViewBean.class.getName());

	@Inject
	private ProductoDao productoDao;
	private List<Producto> listProducto;
	private Producto productoSelected;

	public ProductoViewBean() {
		super();
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
	 * @return the productoSelected
	 */
	public Producto getProductoSelected() {
		return productoSelected;
	}

	/**
	 * @param productoSelected the productoSelected to set
	 */
	public void setProductoSelected(Producto productoSelected) {
		this.productoSelected = productoSelected;
	}

	@PostConstruct
	public void init() {
		try {
			LOGGER.info("Obteniendo Información del Producto");
			obtenerListProducto();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("PRODUCTO", "Error del Sistema.");
		}

	}

	private void obtenerListProducto() throws Exception {
		LOGGER.info("Inicializando Lista del Producto");
		listProducto = productoDao.findAll();
	}

	@Override
	public void inicializar() throws Exception {
		LOGGER.info("Inicializando Producto");
		setAccion("C");
		productoSelected = new Producto();
	}

	@Override
	public void editar(ActionEvent ev) throws Exception {
		setAccion("U");

	}

	@Override
	public void guardarOrModificar(ActionEvent ev) {
		try {
			if (getAccion().equals("C")) {
				LOGGER.info("Guardando Información del Producto");
				productoDao.guardar(productoSelected);
			} else if (getAccion().equals("U")) {
				LOGGER.info("Modificando Información del Producto");
				productoDao.actualizar(productoSelected);

			}
			info("PRODUCTO", getAccion().equals("C") ? "Guardado exitosamente." : "Actualizado exitosamente.");
			obtenerListProducto();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("PRODUCTO", "Error del Sistema.");
		}

	}

	@Override
	public void actualizar(ActionEvent ev) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(ActionEvent ev) {
		try {
			LOGGER.info("Eliminando Información del Producto");
			productoDao.eliminar(productoSelected);
			info("PRODUCTO", "Eliminado exitosamente.");
			obtenerListProducto();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			error("PRODUCTO", "Error del Sistema.");
		}

	}

	@Override
	public void buscar(ActionEvent ev) throws Exception {
		// TODO Auto-generated method stub

	}

}
