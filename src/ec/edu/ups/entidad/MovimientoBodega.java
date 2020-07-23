package ec.edu.ups.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class MovimientoBodega implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoMovimiento;
	
	@ManyToOne
	@JoinColumn
	private Bodega bodega;
	
	@OneToOne
	@JoinColumn
	private Producto producto;
	private int stock;
	
	@Transient
	private boolean editable;
	
	public MovimientoBodega() {
		// TODO Auto-generated constructor stub
	}

	public MovimientoBodega(Bodega bodega, Producto producto, int stock) {
		super();
		this.bodega = bodega;
		this.producto = producto;
		this.stock = stock;
	}
	
	

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getCodigoMovimiento() {
		return codigoMovimiento;
	}

	public void setCodigoMovimiento(int codigoMovimiento) {
		this.codigoMovimiento = codigoMovimiento;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodega == null) ? 0 : bodega.hashCode());
		result = prime * result + codigoMovimiento;
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
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
		MovimientoBodega other = (MovimientoBodega) obj;
		if (bodega == null) {
			if (other.bodega != null)
				return false;
		} else if (!bodega.equals(other.bodega))
			return false;
		if (codigoMovimiento != other.codigoMovimiento)
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovimientoBodega [codigoMovimiento=" + codigoMovimiento + ", bodega=" + bodega + ", producto="
				+ producto + ", stock=" + stock + "]";
	}
	
	

}
