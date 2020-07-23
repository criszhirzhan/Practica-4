package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;


import ec.edu.ups.ejb.RolFacade;
//import ec.edu.ups.ejb.RolFacade;
import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.Rol;
import ec.edu.ups.entidad.Usuario;


@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@ApplicationScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@EJB
	private UsuarioFacade ejbUsuarioFacade;
	@EJB
	private RolFacade ejbRolFacade;
	
	
	private List<Rol> listaRoles;
	private List<Usuario> listaUsuarios;
	private List<Usuario> listaClientes;;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String correo;
	private String contrasena;
	private String estado;
	private String rol;
	
	
	public UsuarioBean() {}
	
	@PostConstruct
	public void init() {
		System.out.println("Listando todos los usuarios"+ ejbUsuarioFacade.findAll());
		System.out.println("listando usuarios");
		listaUsuarios = ejbUsuarioFacade.findAll();
		System.out.println("listando clientes");
		listaClientes = ejbUsuarioFacade.listadoClientes();
		System.out.println("lista de clientes: "+listaClientes);
		System.out.println("rol: "+ejbRolFacade.find(1));
		
	}
	
	//Aqui le agregamos las funcionalidades
	/*
	public List<Rol> obtenerRol() {
		listaRoles = ejbRolFacade.findAll();
		return listaRoles;
	}*/
	
	public Rol buscarRols() {
		Rol r = new Rol();
		System.out.println("recuperando rol");
		System.out.println("codigo del roll: "+rol);
		try {
			r = ejbRolFacade.obtenerRol(this.rol);
		}catch(Exception e) {
			e.getMessage();
		}
		System.out.println("rol recuperado: "+r);
		return r;
	}
	
	
	//agregar empleados o adm
	public String add() {
		
		ejbUsuarioFacade.create(new Usuario(this.cedula, this.nombres, this.apellidos, this.direccion, this.correo, this.correo, this.estado, buscarRols()));
	
		System.out.println("Listando los empleados");
		this.estado = "";
		this.cedula ="";
		this.nombres ="";
		this.apellidos="";
		this.direccion="";
		this.correo="";
		this.contrasena="";
		listaUsuarios = ejbUsuarioFacade.findAll();
		return null;
	}
	
	public String addClienteInactivo() {
		String estado = "inactivo";
		
		Rol rolss = ejbRolFacade.find(1);
		
		ejbUsuarioFacade.create(new Usuario(this.cedula, this.nombres, this.apellidos, this.direccion, estado, rolss));
		//this.cedula="";
		this.nombres="";
		this.apellidos="";
		this.direccion="";
		estado ="";
		listaUsuarios = ejbUsuarioFacade.findAll();
		return null;
	}
	
	
	
	public String iniciarSesion() {
		
		Usuario usuario = ejbUsuarioFacade.validarIngreso(this.correo, this.contrasena);
		this.correo = "";
		this.contrasena ="";
		
		if(usuario != null) {
			return "exito"+usuario;
			
		}else {
			return "fallo";
		}
	}
	
	
	public String remove(Usuario u) {
		ejbUsuarioFacade.remove(u);
		ejbUsuarioFacade.findAll();
		return null;
	}
	
	
	
	

	public UsuarioFacade getEjbUsuarioFacade() {
		return ejbUsuarioFacade;
	}

	public void setEjbUsuarioFacade(UsuarioFacade ejbUsuarioFacade) {
		this.ejbUsuarioFacade = ejbUsuarioFacade;
	}

	public RolFacade getEjbRolFacade() {
		return ejbRolFacade;
	}

	public void setEjbRolFacade(RolFacade ejbRolFacade) {
		this.ejbRolFacade = ejbRolFacade;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Usuario> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Usuario> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
