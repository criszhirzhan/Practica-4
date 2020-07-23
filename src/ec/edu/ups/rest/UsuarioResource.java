package ec.edu.ups.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;

import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.RolFacade;
import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.Persona;
import ec.edu.ups.entidad.Rol;
import ec.edu.ups.entidad.Usuario;

@Path("/Usuarios")
public class UsuarioResource {
	
	@EJB
	private UsuarioFacade ejbUsuarioFacade;
	@EJB
	private RolFacade ejbRolFacade;
	@EJB
	private PersonaFacade ejbPersonaFacade;
	
	private Usuario usuario;
	private Rol rol;
	
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listadoUsuarios() {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Jsonb jsonb = JsonbBuilder.create();
		
		listaUsuarios = ejbUsuarioFacade.findAll();
		System.out.println("usuarios recuperados: "+listaUsuarios);
		
		return Response.ok(jsonb.toJson(listaUsuarios))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	@GET
	@Path("/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarUsuario(@PathParam("cedula") String cedula) {
		Usuario usu = new Usuario();
		Jsonb jsonb = JsonbBuilder.create();
		usu = ejbUsuarioFacade.find(cedula);
		System.out.println("usuario recuperado: "+usu);
		
		return Response.ok(jsonb.toJson(usu))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
		
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registrar(@FormParam("nombre") String nombre, @FormParam("apellido") String apellido, @FormParam("cedula") String cedula, 
			@FormParam("direccion") String direccion, @FormParam("correo") String correo, @FormParam("contrasena") String contrasena) {
		
		System.out.println("creando nuevo usuario");
		rol = ejbRolFacade.find(1);
		System.out.println("rol de cliente: "+rol);
		String estado = "activo";
		usuario = new Usuario(cedula, nombre, apellido, direccion, correo, contrasena, rol);
		System.out.println("persistiendo usuario");
		ejbUsuarioFacade.create(usuario);
		
		return Response.ok("exito")
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	
	@POST
	@Path("/activar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response activarCuentaCliente(@FormParam("contrasena") String contrasena, @FormParam("cedula") String cedula) {
		
		System.out.println("iniciando activacion de cuenta de usuario");
		
		Persona persona = new Persona();
		Rol rol = new Rol();
		rol = ejbRolFacade.find(1);
		System.out.println("rol recuperado: "+rol);
		persona = ejbPersonaFacade.find(cedula);
		
		persona.setEstado("activo");
		
		ejbPersonaFacade.edit(persona);
		
		System.out.println("persona recuperada: "+persona);
		Usuario usuario = new Usuario(persona.getCedula(), persona.getNombre(), persona.getApellido(), persona.getDireccion(),
										persona.getCorreo(), contrasena, "activo", rol);
		System.out.println("creando usuario");
		ejbUsuarioFacade.create(usuario);
		System.out.println("finaliza proceso");
		
		
		return Response.ok("usuario activado")
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
		
	}
	
	/*
	@POST
	@Path("/inicio")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response inicio(@FormParam("correo") String correo, @FormParam("contrasena") String contrasena) {
		
		Usuario usu = new Usuario();
		System.out.println("validando ingreso de usuario");
		usu = ejbUsuarioFacade.validarIngresoPorRol(correo, contrasena);
		System.out.println("usuario recuperado: "+usu);
		
		return Response.status(201).entity("usuario salvado: "+usu)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
		
	}*/

}
