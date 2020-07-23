package ec.edu.ups.entidad;

import java.io.Serializable;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: FacturaDetalle
 *
 */
@Entity

public class FacturaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoFactDet;
	private int cantidad;
	
	@ManyToOne
	@JoinColumn
	private Producto producto;

	
	@ManyToOne
	@JoinColumn
	private Factura factura;
	
    @Transient
    private boolean editable;
	

	public FacturaDetalle() {
	
	}


	public FacturaDetalle( int cantidad, Producto producto,  Factura factura) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.factura = factura;
	}
	
	


	public FacturaDetalle(int codigoFactDet, int cantidad, Producto producto, Factura factura) {
		super();
		this.codigoFactDet = codigoFactDet;
		this.cantidad = cantidad;
		this.producto = producto;
		this.factura = factura;
	}
	
	


	public boolean isEditable() {
		return editable;
	}


	public void setEditable(boolean editable) {
		this.editable = editable;
	}


	public int getCodigoFactDet() {
		return codigoFactDet;
	}


	public void setCodigoFactDet(int codigoFactDet) {
		this.codigoFactDet = codigoFactDet;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}





	public Factura getFactura() {
		return factura;
	}


	public void setFactura(Factura factura) {
		this.factura = factura;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + codigoFactDet;
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
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
		FacturaDetalle other = (FacturaDetalle) obj;
		if (cantidad != other.cantidad)
			return false;
		if (codigoFactDet != other.codigoFactDet)
			return false;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}


	

   
}
