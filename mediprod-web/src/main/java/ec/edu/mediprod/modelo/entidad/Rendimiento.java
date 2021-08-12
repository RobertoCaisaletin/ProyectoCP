package ec.edu.mediprod.modelo.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rendimiento database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Rendimiento.findByProceso", query="SELECT r FROM Rendimiento r WHERE r.proceso =:proceso ORDER BY r.hora"),
@NamedQuery(name="Rendimiento.findByProcesoAndUsuario", query="SELECT r FROM Rendimiento r WHERE r.proceso =:proceso AND r.usuario =:usuario ORDER BY r.hora"),
})
public class Rendimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rendimiento")
	private Integer idRendimiento;

	private Integer cantidad;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hora;

	private String observacion;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="id_proceso")
	private Proceso proceso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Rendimiento() {
	}

	public Integer getIdRendimiento() {
		return this.idRendimiento;
	}

	public void setIdRendimiento(Integer idRendimiento) {
		this.idRendimiento = idRendimiento;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}