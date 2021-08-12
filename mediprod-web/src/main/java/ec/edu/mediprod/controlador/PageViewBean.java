package ec.edu.mediprod.controlador;

import javax.faces.event.ActionEvent;

public abstract class PageViewBean extends BaseViewBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5373560724861594465L;

	public abstract void inicializar() throws Exception;

	public abstract void editar(ActionEvent ev) throws Exception;

	public abstract void guardarOrModificar(ActionEvent ev) throws Exception;

	public abstract void actualizar(ActionEvent ev) throws Exception;

	public abstract void eliminar(ActionEvent ev) throws Exception;

	public abstract void buscar(ActionEvent ev) throws Exception;

}
