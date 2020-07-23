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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.MovimientoBodegaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.MovimientoBodega;
import ec.edu.ups.entidad.Producto;
import ec.edu.ups.entidad.Usuario;

@Path("/service2")
public class ApiRest2 {
	
	@EJB
	private ProductoFacade ejbProductoFacade;
	@EJB
	private UsuarioFacade ejbUsuarioFacade;
	@EJB
	private MovimientoBodegaFacade ejbMovBodFacade;

	
	
	@GET
	@Path("/productos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProductos() {
		List<Producto> listaProductos = new ArrayList<Producto>();
		Jsonb jsonb = JsonbBuilder.create();
		
		listaProductos = ejbProductoFacade.listadoProdRest();
		
		/*System.out.println("productos: "+listaProductos);
		String desc = jsonb.toJson(list)*/
	
		return Response.ok(jsonb.toJson(listaProductos))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	//Para obtener productos por bodegas, se le pasa el id
	@POST
	@Path("/Probodegas")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProductosBodega(@FormParam("bodega") int codigo) {
		List<MovimientoBodega> listaProdBodega = new ArrayList<MovimientoBodega>();
		
		listaProdBodega = ejbMovBodFacade.listarBodegaProductos(codigo);
		
		return Response.status(201).entity(listaProdBodega)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	
	/*Para seleccionar un producto*/
	@GET
	@Path("/productos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Producto escogerProducto(@PathParam("id") int id) {
		
		Producto producto = new Producto();
		producto = ejbProductoFacade.find(id);
		System.out.println("retornando producto escogido:"+producto);
		
		return producto;
		
	}
	
	
	/*Para hacer login*/
	@POST
	@Path("/inicio")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response inicio(@FormParam("correo") String correo, @FormParam("contrasena") String contrasena) {
		
		Usuario usu = new Usuario();
		System.out.println("validando ingreso de usuario");
		try {
			usu = ejbUsuarioFacade.validarIngresoPorRol(correo, contrasena);
			if(usu != null) {
				System.out.println("usuario recuperado: "+usu);
				
				return Response.status(201).entity(usu.getCedula())
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
						.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
			}
			
		}catch(Exception ex) {
			return Response.status(204).build();
		}
		return Response.status(204)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
				
	}

	
	
		

}
