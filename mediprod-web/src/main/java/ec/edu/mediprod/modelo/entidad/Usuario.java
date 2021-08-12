package ec.edu.mediprod.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findUserByUsernameAndPass", query="SELECT u FROM Usuario u WHERE u.username =:username AND u.password =:password AND u.estado='ACTIVO'")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer idUsuario;

	private String estado;

	@Column(name="nom_usuario")
	private String nomUsuario;

	private String password;

	private String username;

	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;

	public Usuario() {
	}
	
	
	

	public Usuario(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}




	public Usuario(Integer idUsuario) {
		super();
		this.idUsuario = idUsuario;
	}



	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNomUsuario() {
		return this.nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}