package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidad.Categoria;
import ec.edu.ups.entidad.FacturaDetalle;
import ec.edu.ups.entidad.Producto;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@ApplicationScoped
public class ProductoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@EJB
	private ProductoFacade ejbProducto;
	@EJB
	private CategoriaFacade ejbCategoria;
	
	private Producto producto;
	private String nombre;
	private double precioCompra;
	private double precioVenta;
	private int stock;
	private Date fechaCompra;
	private String marca;
	
	private int codigoP;
	
	private Categoria categoria;
	private String nombreCate;
	
	private String resultado;
	
	private List<Producto> listaProducto;
	private List<Categoria> listaCategoria;
	private List<FacturaDetalle> facturasDetalle;
	
	
	public ProductoBean() {
		super();
	}

	@PostConstruct
	public void init() {
		listaProducto = ejbProducto.findAll();
		listaProducto = ejbProducto.listarProductos();
		System.out.println("Listado Productos");
		listaCategoria = ejbCategoria.findAll();
		//listaCategoria = ejbCategoria.listarCategorias();
		System.out.println("Listado Categorias");
	}
	
	public void obtenerCategoria(AjaxBehaviorEvent evento) {
		this.categoria = new Categoria(this.nombreCate);	
		this.categoria = ejbCategoria.find(this.nombreCate); 
		if (categoria != null) {
			this.resultado = categoria.getCodigoCategoria() + " " + categoria.getNombreCategoria(); 
		} else {
			this.resultado = "No existe.."; 
		}		 
	}
	
	public String add() {
		fechaCompra = new Date();
		System.out.println("CODIGO: "+this.codigoP);
		categoria = ejbCategoria.find(this.codigoP);
		ejbProducto.create(new Producto(this.nombre,this.categoria,this.precioCompra,this.precioVenta,this.stock,this.fechaCompra,this.marca));
		listaProducto = ejbProducto.findAll();
		return null;
	}
	
	public String delete(Producto p) {
		ejbProducto.remove(p);
		listaProducto = ejbProducto.findAll();
		return null;
	}
	
	public String edit(Producto p) {
		p.setEditable(true);
		return null;
	}
	
	public String save(Producto p) {
		ejbProducto.edit(p);
		p.setEditable(false);
		return null;
	}
	
	
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNombreCate() {
		return nombreCate;
	}

	public void setNombreCate(String nombreCate) {
		this.nombreCate = nombreCate;
	}

	public ProductoFacade getEjbProducto() {
		return ejbProducto;
	}


	public void setEjbProducto(ProductoFacade ejbProducto) {
		this.ejbProducto = ejbProducto;
	}


	public CategoriaFacade getEjbCategoria() {
		return ejbCategoria;
	}


	public void setEjbCategoria(CategoriaFacade ejbCategoria) {
		this.ejbCategoria = ejbCategoria;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getPrecioCompra() {
		return precioCompra;
	}


	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}


	public double getPrecioVenta() {
		return precioVenta;
	}


	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Date getFechaCompra() {
		return fechaCompra;
	}


	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
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


	public List<FacturaDetalle> getFacturasDetalle() {
		return facturasDetalle;
	}


	public void setFacturasDetalle(List<FacturaDetalle> facturasDetalle) {
		this.facturasDetalle = facturasDetalle;
	}

	public int getCodigoP() {
		return codigoP;
	}

	public void setCodigoP(int codigoP) {
		this.codigoP = codigoP;
	}
	
	
	
	

}
