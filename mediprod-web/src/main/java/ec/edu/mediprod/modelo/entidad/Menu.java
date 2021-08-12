package ec.edu.mediprod.modelo.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findByPerfilAndUsuario", query="SELECT m FROM Menu m WHERE m.perfil =:perfil AND m.estado='HABILITADO'")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_menu")
	private Integer idMenu;

	private String estado;

	private String icono;

	@Column(name="nom_item")
	private String nomItem;

	private String path;

	@Column(name="tipo_item")
	private String tipoItem;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name="id_menu_padre")
	private Menu subMenu;

	public Menu() {
	}

	public Integer getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	

	/**
	 * @return the subMenu
	 */
	public Menu getSubMenu() {
		return subMenu;
	}

	/**
	 * @param subMenu the subMenu to set
	 */
	public void setSubMenu(Menu subMenu) {
		this.subMenu = subMenu;
	}

	public String getNomItem() {
		return this.nomItem;
	}

	public void setNomItem(String nomItem) {
		this.nomItem = nomItem;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTipoItem() {
		return this.tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}