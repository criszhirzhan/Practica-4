package ec.edu.ups.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoCategoria;
	
	private String nombreCategoria;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="categoria")
	private List<Producto> productos;
	
	@Transient
	private boolean editable;
	
	
	public Categoria() {
		
	}


	public Categoria(String nombreCategoria) {
		super();
		this.nombreCategoria = nombreCategoria;
	}


	public int getCodigoCategoria() {
		return codigoCategoria;
	}


	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}


	public String getNombreCategoria() {
		return nombreCategoria;
	}


	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
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
		result = prime * result + codigoCategoria;
		result = prime * result + ((nombreCategoria == null) ? 0 : nombreCategoria.hashCode());
		result = prime * result + ((productos == null) ? 0 : productos.hashCode());
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
		Categoria other = (Categoria) obj;
		if (codigoCategoria != other.codigoCategoria)
			return false;
		if (nombreCategoria == null) {
			if (other.nombreCategoria != null)
				return false;
		} else if (!nombreCategoria.equals(other.nombreCategoria))
			return false;
		if (productos == null) {
			if (other.productos != null)
				return false;
		} else if (!productos.equals(other.productos))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Categoria [codigoCategoria=" + codigoCategoria + ", nombreCategoria=" + nombreCategoria + ", productos="
				+ productos + ", editable=" + editable + "]";
	}
	
   
}
