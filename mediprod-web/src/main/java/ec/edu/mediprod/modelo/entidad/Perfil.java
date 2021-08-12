package ec.edu.mediprod.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@NamedQuery(name="Perfil.findByEstado", query="SELECT p FROM Perfil p WHERE p.estado =:estado ORDER BY p.descripcion")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_perfil")
	private Integer idPerfil;

	private String descripcion;

	private String estado;

	public Perfil() {
	}
	
	

	public Perfil(Integer idPerfil) {
		super();
		this.idPerfil = idPerfil;
	}



	public Integer getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}