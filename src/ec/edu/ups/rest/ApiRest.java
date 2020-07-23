package ec.edu.ups.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import ec.edu.ups.ejb.FacturaFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.RolFacade;
import ec.edu.ups.entidad.Factura;

//import ec.edu.ups.entidad.Rol;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@Path("/services")
public class ApiRest {

	@EJB
	private RolFacade ejbRolFacade;
	
	@EJB
	private PersonaFacade ejbPersonaFacade;
	
	@EJB
	private FacturaFacade ejbFactFacade;
	
	private List<Factura> facturas;
	
	//private List<Rol> roles;
	

	
	@GET
	@Path("/facturas/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFacturas() {
		facturas = new ArrayList<Factura>();
		//ejbFactFacade = new FacturaFacade();
		Jsonb jsonb = JsonbBuilder.create();
		facturas=ejbFactFacade.findAll();
		System.out.println("Facturas desde REST: ...."+facturas.toString());
		
		return Response.ok(jsonb.toJson(facturas)).build();
		
	}
	

}
