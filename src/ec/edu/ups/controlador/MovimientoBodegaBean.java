package ec.edu.ups.controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.MovimientoBodegaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidad.Bodega;
import ec.edu.ups.entidad.MovimientoBodega;
import ec.edu.ups.entidad.Producto;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@ApplicationScoped
public class MovimientoBodegaBean {
	
	@EJB
	private MovimientoBodegaFacade ejbMovBode;
	@EJB
	private ProductoFacade ejbProductoFacade;
	@EJB
	private BodegaFacade ejbBodegaFacade;
	
	private List<Producto> listaProductos;
	private List<Bodega> listaBodegas;
	private List<MovimientoBodega> movimientosLista;
	
	private Bodega bodega;
	
	private int productoID;
	private int bodegaID;
	private int stock;
	private int stockProducto;
	private String nombreBodega;
	
	@PostConstruct
	public void init() {
		
		listaProductos = ejbProductoFacade.findAll();
		System.out.println("listando productos: "+listaProductos);
		listaBodegas = ejbBodegaFacade.findAll();
		System.out.println("Lista de bodegas: "+listaBodegas);	
	}
	
	
	/*public void obtenerBodega(AjaxBehaviorEvent evento) {
		System.out.println("Buscando bodega");
		if(this.bodegaID > 0) {
			this.bodega = ejbBodegaFacade.find(this.bodegaID);
			
			if(this.bodega != null ) {
				this.nombreBodega = bodega.getNombre();
			}else {
				this.nombreBodega = "Bodega no registrada";
			}
		}else {
			this.bodegaID = 0;
		}
		System.out.println("Nombre de la bodega:"+nombreBodega);
	}*/
	
	public String addProdsToBodega() {
		Bodega bod = new Bodega();
		bod = ejbBodegaFacade.find(this.bodegaID);
		System.out.println("bodega recuperada: "+bod);
		Producto pr = new Producto();
		pr = ejbProductoFacade.find(this.productoID);
		System.out.println("producto recuperado: "+pr);
		
		if(bod != null && pr != null) {
			ejbMovBode.create(new MovimientoBodega(bod, pr, this.stock));
			movimientosLista = ejbMovBode.listarBodegaProductos(bod.getCodigoBodega());
			bod.setInventario(movimientosLista);
	
		}
		
		return null;
	}
	
	public String add() {
		Producto pro = new Producto();
		pro = ejbProductoFacade.find(this.productoID);
		System.out.println("producto recuperado: "+pro);
		
		Bodega bodega = new Bodega();
		bodega = ejbBodegaFacade.find(this.bodegaID);
		System.out.println("bodega recuperada: "+bodega);
		stockProducto = pro.getStock();
		System.out.println("stock del producto seleccionado: "+stockProducto);
		if(stock <= stockProducto) {
			ejbMovBode.create(new MovimientoBodega(bodega, pro, this.stock));
			System.out.println("agregado movimiento de bodega");
			
		}else if(this.stock> stockProducto) {
			System.out.println("error, no se puede ingresar un stock mayor al stock del producto");
		}
		this.stock=0;
		
		return null;
	}
	
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<Bodega> getListaBodegas() {
		return listaBodegas;
	}

	public void setListaBodegas(List<Bodega> listaBodegas) {
		this.listaBodegas = listaBodegas;
	}


	public int getProductoID() {
		return productoID;
	}


	public void setProductoID(int productoID) {
		this.productoID = productoID;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public int getStockProducto() {
		return stockProducto;
	}


	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}


	public int getBodegaID() {
		return bodegaID;
	}


	public void setBodegaID(int bodegaID) {
		this.bodegaID = bodegaID;
	}


	public Bodega getBodega() {
		return bodega;
	}


	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}


	public String getNombreBodega() {
		return nombreBodega;
	}


	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}


	public List<MovimientoBodega> getMovimientosLista() {
		return movimientosLista;
	}


	public void setMovimientosLista(List<MovimientoBodega> movimientosLista) {
		this.movimientosLista = movimientosLista;
	}
	
}
