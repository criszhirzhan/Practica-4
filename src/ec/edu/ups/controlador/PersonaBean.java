package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.FacturaFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.entidad.Factura;
import ec.edu.ups.entidad.Persona;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private PersonaFacade ejbPersonaFacade;
	private FacturaFacade ejbFacturaFacade;
	private String cedula;
	private String apellido;
	private String correo;
	private String direccion;
	private String nombre;
	private Persona persona;
	private List<Factura> listaFacturas;
	private List<Persona> listaPersonas;
	private Persona per;

	public PersonaBean() {
	}

	@PostConstruct
	public void init() {
		System.out.println("listando clientes: ");
		listaPersonas = ejbPersonaFacade.findAll();
		System.out.println("listado de clientes: "+listaPersonas);
	}

	public String add() {
		System.out.println("registrando cliente");


		ejbPersonaFacade.create(new Persona(this.cedula, this.nombre, this.apellido, this.direccion, this.correo, "activo"));
		Limpiar();
		listaPersonas = ejbPersonaFacade.listarClientesActivos();

		return "clienteR";
	}

	public String update() {
	
		per.setCedula(this.cedula);
		per.setApellido(this.apellido);
		per.setDireccion(this.direccion);
		per.setCorreo(this.correo);
		per.setNombre(this.nombre);
		
		ejbPersonaFacade.edit(per);
		
		Limpiar();
		listaPersonas = ejbPersonaFacade.listarClientesActivos();
		return "actualizado";
	}

	public List<Persona> listarPersonas() {
		System.out.println(persona.getCedula());
		listaPersonas = ejbPersonaFacade.listarClientesActivos();
		return listaPersonas;
	}
	
	
	public void Limpiar() {
		
		this.cedula = "";
		this.apellido = "";
		this.correo = "";
		this.direccion = "";
		this.nombre = "";
	}
	

	public String editar(Persona p) {

		this.cedula = p.getCedula();
		this.apellido = p.getApellido();
		this.nombre = p.getNombre();
		this.direccion = p.getDireccion();
		this.correo = p.getCorreo();
		
		per=p;
		
		listaPersonas = ejbPersonaFacade.listarClientesActivos();
		return "editar";
		
	}
	
	
	public String delete(Persona p) {
		p.setEstado("inactivo");
		ejbPersonaFacade.edit(p);
		listaPersonas = ejbPersonaFacade.listarClientesActivos();
		return "eliminado";
	}
	

	////////////////////////////////////////////////////
	public PersonaFacade getEjbPersonaFacade() {
		return ejbPersonaFacade;
	}

	public void setEjbPersonaFacade(PersonaFacade ejbPersonaFacade) {
		this.ejbPersonaFacade = ejbPersonaFacade;
	}

	public FacturaFacade getEjbFacturaFacade() {
		return ejbFacturaFacade;
	}

	public void setEjbFacturaFacade(FacturaFacade ejbFacturaFacade) {
		this.ejbFacturaFacade = ejbFacturaFacade;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

}
