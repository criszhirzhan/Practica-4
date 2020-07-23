package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidad.Categoria;
import ec.edu.ups.entidad.Producto;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@ApplicationScoped
public class CategoriaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EJB
	private CategoriaFacade ejbCategoria;
	@EJB
	private ProductoFacade ejbProducto;
	private String nombre;
	
	private Categoria categoria;
	private Producto producto;
	
	private List<Producto> listaProducto;
	private List<Categoria> listaCategoria;
	
	
	
	public CategoriaBean() {
		super();
	}


	@PostConstruct
	public void init() {
		System.out.println();
		listaCategoria = ejbCategoria.findAll();
		System.out.println("Listado Categorias");
		//listaProducto = ejbProducto.findAll();
		System.out.println("Listado Productos");
	}

	public String add() {
		System.out.println("creando categora: "+this.nombre);	
		ejbCategoria.create(new Categoria(this.nombre));
		this.nombre="";
		listaCategoria = ejbCategoria.findAll();
		System.out.println("categorias: "+listaCategoria);
		return null;
	}
	
	public String delete(Categoria c) {
		ejbCategoria.remove(c);
		listaCategoria = ejbCategoria.findAll();
		return null;
	}
	
	public String edit(Categoria c) {
		c.setEditable(true);
		return null;
	}
	
	public String save(Categoria c) {
		ejbCategoria.edit(c);
		c.setEditable(false);
		return null;
	}

	public CategoriaFacade getEjbCategoria() {
		return ejbCategoria;
	}


	public void setEjbCategoria(CategoriaFacade ejbCategoria) {
		this.ejbCategoria = ejbCategoria;
	}


	public ProductoFacade getEjbProducto() {
		return ejbProducto;
	}


	public void setEjbProducto(ProductoFacade ejbProducto) {
		this.ejbProducto = ejbProducto;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public List<Producto> getListaProducto() {
		return listaProducto;
	}


	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}


	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}


	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}
	
}
