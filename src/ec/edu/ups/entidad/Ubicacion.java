package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ubicacion
 *
 */
@Entity

public class Ubicacion implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoUbicacion;
	
	private String provincia;
	private String ciudad;
	private String callePrincipal;
	private String calleSecundaria;
	private String numero;
	


	
	public Ubicacion() {
		
	}

	public Ubicacion( String provincia, String ciudad, String callePrincipal,
			String calleSecundaria, String numero) {
		super();
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.callePrincipal = callePrincipal;
		this.calleSecundaria = calleSecundaria;
		this.numero = numero;
	}
	
	

	public int getCodigoUbicacion() {
		return codigoUbicacion;
	}

	public void setCodigoUbicacion(int codigoUbicacion) {
		this.codigoUbicacion = codigoUbicacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Ubicacion [codigoUbicacion=" + codigoUbicacion + ", provincia=" + provincia + ", ciudad=" + ciudad
				+ ", callePrincipal=" + callePrincipal + ", calleSecundaria=" + calleSecundaria + ", numero=" + numero
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((callePrincipal == null) ? 0 : callePrincipal.hashCode());
		result = prime * result + ((calleSecundaria == null) ? 0 : calleSecundaria.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + codigoUbicacion;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
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
		Ubicacion other = (Ubicacion) obj;

		if (callePrincipal == null) {
			if (other.callePrincipal != null)
				return false;
		} else if (!callePrincipal.equals(other.callePrincipal))
			return false;
		if (calleSecundaria == null) {
			if (other.calleSecundaria != null)
				return false;
		} else if (!calleSecundaria.equals(other.calleSecundaria))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (codigoUbicacion != other.codigoUbicacion)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}

	
	
	
	
   
}
