package ec.edu.mediprod.vista;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ec.edu.mediprod.controlador.BaseViewBean;
import ec.edu.mediprod.modelo.dao.MenuDao;
import ec.edu.mediprod.modelo.entidad.Menu;

@SessionScoped
@Named("homeViewBean")
public class HomeViewBean extends BaseViewBean implements Serializable {

	private static final long serialVersionUID = 4687707120995521251L;

	private final static Logger LOGGER = Logger.getLogger(HomeViewBean.class.getName());

	private List<Menu> listMenuPerfil;
	private MenuModel menuModel;
	@Inject
	private MenuDao menuDao;

	public HomeViewBean() {
		super();
		try {
			generarMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the listMenuPerfil
	 */
	public List<Menu> getListMenuPerfil() {
		return listMenuPerfil;
	}

	/**
	 * @param listMenuPerfil the listMenuPerfil to set
	 */
	public void setListMenuPerfil(List<Menu> listMenuPerfil) {
		this.listMenuPerfil = listMenuPerfil;
	}

	/**
	 * @return the menuModel
	 */
	public MenuModel getMenuModel() {
		return menuModel;
	}

	/**
	 * @param menuModel the menuModel to set
	 */
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public void salir() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	@PostConstruct
	public void init() {
		try {
			generarMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void invalidateUsuarioSession() {
		try {
			if (getUsuarioSesion() == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("error.jsf");
			}
		} catch (Exception e) {
		}

	}

	public void generarMenu() throws Exception {
		LOGGER.info("GENERANDO MENU POR USUARIO");
		obtenerMenuPorUsuarioAndPerfil();
		menuModel = new DefaultMenuModel();
		if (listMenuPerfil != null && !listMenuPerfil.isEmpty() && getUsuarioSesion() != null) {
			builderMenuItem();

		}

	}

	private void builderMenuItem() {
		for (Menu menu : listMenuPerfil) {
			if (menu.getTipoItem().equals("M")) {
				builderMenuPrincipal(menu);
			} else {
				builderSubMenu(menu);
			}
		}
	}

	private void builderSubMenu(Menu menu) {
		if (menu.getSubMenu() == null) {
			DefaultMenuItem item = DefaultMenuItem.builder().value(menu.getNomItem()).build();
			item.setUrl(menu.getPath());
			menuModel.getElements().add(item);
		}
	}

	private void builderMenuPrincipal(Menu menu) {
		DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label(menu.getNomItem()).build();
		for (Menu menuItem : listMenuPerfil) {
			Menu submenu = menuItem.getSubMenu();
			if (submenu != null) {
				if (submenu.getIdMenu() == menu.getIdMenu()) {
					DefaultMenuItem item = DefaultMenuItem.builder().value(menuItem.getNomItem()).build();
					item.setUrl(menuItem.getPath());
					firstSubmenu.getElements().add(item);
				}
			}
		}
		menuModel.getElements().add(firstSubmenu);
	}

	private void obtenerMenuPorUsuarioAndPerfil() throws Exception {
		LOGGER.info("OBTENIENDO MENU POR ROL Y USUARIO");
		listMenuPerfil = menuDao.findMenuByPerfilAndUsuario(getUsuarioSesion());
	}

}
