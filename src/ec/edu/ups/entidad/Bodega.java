package ec.edu.ups.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bodega
 *
 */
@Entity

public class Bodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigoBodega;

	private String nombre;

	@OneToOne
	@JoinColumn
	private Ubicacion ubicacion;

	@OneToOne
	@JoinColumn
	private Usuario administrador;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="bodega")
	private List<MovimientoBodega> inventario;
	
	@Transient
	private boolean editable;


	public Bodega() {

	}

	
	public Bodega(String nombre, Ubicacion ubicacion, Usuario administrador) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.administrador = administrador;
	}


	public int getCodigoBodega() {
		return codigoBodega;
	}

	public void setCodigoBodega(int codigoBodega) {
		this.codigoBodega = codigoBodega;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Usuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
	}

	public List<MovimientoBodega> getInventario() {
		return inventario;
	}

	public void setInventario(List<MovimientoBodega> inventario) {
		this.inventario = inventario;
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
		result = prime * result + ((administrador == null) ? 0 : administrador.hashCode());
		result = prime * result + codigoBodega;
		result = prime * result + ((inventario == null) ? 0 : inventario.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((ubicacion == null) ? 0 : ubicacion.hashCode());
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
		Bodega other = (Bodega) obj;
		if (administrador == null) {
			if (other.administrador != null)
				return false;
		} else if (!administrador.equals(other.administrador))
			return false;
		if (codigoBodega != other.codigoBodega)
			return false;
		if (inventario == null) {
			if (other.inventario != null)
				return false;
		} else if (!inventario.equals(other.inventario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (ubicacion == null) {
			if (other.ubicacion != null)
				return false;
		} else if (!ubicacion.equals(other.ubicacion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bodega [codigoBodega=" + codigoBodega + ", nombre=" + nombre + ", ubicacion=" + ubicacion
				+ ", administrador=" + administrador + ", inventario=" + inventario + ", editable=" + editable + "]";
	}
	

}
