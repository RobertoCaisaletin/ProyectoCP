package ec.edu.mediprod.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Integer idProducto;

	private String descripcion;

	@Column(name = "num_estandar")
	private Integer numEstandar;

	public Producto() {
	}
	
	

	public Producto(Integer idProducto) {
		super();
		this.idProducto = idProducto;
	}



	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNumEstandar() {
		return this.numEstandar;
	}

	public void setNumEstandar(Integer numEstandar) {
		this.numEstandar = numEstandar;
	}

}