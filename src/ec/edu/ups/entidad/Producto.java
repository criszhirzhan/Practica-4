package ec.edu.ups.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto
 *
 */
@Entity

public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoProducto;
	private String nombre;
	
	@ManyToOne
	@JoinColumn
	private Categoria categoria;
	
	private double precioCompra;
	private double precioVenta;
	private int stock;
	private Date fechaCompra;
	private String marca;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="producto")
	private List<FacturaDetalle> facturasDetalle;
	
	@ManyToMany(mappedBy = "productos")
	private List<Pedido> pedidos;


	@Transient
	private boolean editable;
	 
	public Producto() {
		
	}



	public Producto(String nombre, Categoria categoria, double precioCompra, double precioVenta, int stock, Date fechaCompra, String marca) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.stock = stock;
		this.fechaCompra = fechaCompra;
		this.marca = marca;
	}


	

	public List<FacturaDetalle> getFacturasDetalle() {
		return facturasDetalle;
	}



	public void setFacturasDetalle(List<FacturaDetalle> facturasDetalle) {
		this.facturasDetalle = facturasDetalle;
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



	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
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


	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + codigoProducto;
		result = prime * result + ((fechaCompra == null) ? 0 : fechaCompra.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precioCompra);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(precioVenta);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + stock;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigoProducto != other.codigoProducto)
			return false;
		if (fechaCompra == null) {
			if (other.fechaCompra != null)
				return false;
		} else if (!fechaCompra.equals(other.fechaCompra))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precioCompra) != Double.doubleToLongBits(other.precioCompra))
			return false;
		if (Double.doubleToLongBits(precioVenta) != Double.doubleToLongBits(other.precioVenta))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [codigoProducto=" + codigoProducto + ", nombre=" + nombre + ", categoria=" + categoria
				+ ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", stock=" + stock
				+ ", fechaCompra=" + fechaCompra + ", marca=" + marca + ", facturasDetalle=" + facturasDetalle
				+ ", editable=" + editable + "]";
	}

}
