package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.entidad.Factura;
import ec.edu.ups.entidad.Persona;


@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

	@PersistenceContext(unitName = "Practica-4")
    private EntityManager em;
  
	public PersonaFacade() {
		super(Persona.class);
	}

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
	public List<Persona> listarClientesActivos() {
		Query nativeQuery = em.createNativeQuery("SELECT * FROM persona WHERE estado='activo' ", Persona.class);

		return (List<Persona>)nativeQuery.getResultList();
	}
}
