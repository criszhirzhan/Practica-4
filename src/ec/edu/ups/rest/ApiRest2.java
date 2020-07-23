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

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.Producto;
import ec.edu.ups.entidad.Usuario;

@Path("/service2")
public class ApiRest2 {
	
	@EJB
	private ProductoFacade ejbProductoFacade;
	@EJB
	private UsuarioFacade ejbUsuarioFacade;

	
	
	@GET
	@Path("/productos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProductos() {
		List<Producto> listaProductos = new ArrayList<Producto>();
		Jsonb jsonb = JsonbBuilder.create();
		System.out.println("listando productos");
		listaProductos = ejbProductoFacade.findAll();
		System.out.println("productos: "+listaProductos);
		return Response.ok(jsonb.toJson(listaProductos))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	

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
		
	}

	
	
		

}
