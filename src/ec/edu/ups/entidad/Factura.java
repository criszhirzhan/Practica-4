package ec.edu.ups.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Factura
 *
 */
@Entity
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoFactura;
	
	private Date fecha;
	
    @Transient
    private boolean editable;
	

	@ManyToOne
	@JoinColumn
	private Persona persona;
	
	@ManyToOne
	@JoinColumn
	private Bodega bodega;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="factura")
	private List<FacturaDetalle> detallesFactura;
	
	private double total;
	private double iva;
	private double subtotal;
	private String estadoFactura;

	public Factura() {
		
	}

	public Factura(Date fecha, Persona persona) {
		super();
		this.fecha = fecha;
		this.persona = persona;

	}

	public int getCodigoFactura() {
		return codigoFactura;
	}

	public void setCodigoFactura(int codigoFactura) {
		this.codigoFactura = codigoFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<FacturaDetalle> getDetallesFactura() {
		return detallesFactura;
	}

	public void setDetallesFactura(List<FacturaDetalle> detallesFactura) {
		this.detallesFactura = detallesFactura;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public String getEstadoFactura() {
		return estadoFactura;
	}

	public void setEstadoFactura(String estadoFactura) {
		this.estadoFactura = estadoFactura;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}



	
	
   
}
