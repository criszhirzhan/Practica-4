package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.entidad.Factura;
import ec.edu.ups.entidad.Ubicacion;

@Stateless
public class UbicacionFacade  extends AbstractFacade<Ubicacion> {

	 @PersistenceContext(unitName = "Practica-4")
	    private EntityManager em;
	
	public UbicacionFacade() {
		super(Ubicacion.class);
	}
	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
	public Ubicacion buscarUbicacion(String provincia, String ciudad, String numero) {
		Query nativeQuery = em.createNativeQuery("SELECT * FROM ubicacion WHERE provincia = ? AND ciudad =? AND numero =?", Ubicacion.class);
		nativeQuery.setParameter(1, provincia);
		nativeQuery.setParameter(2, ciudad);
		nativeQuery.setParameter(3, numero);

		return (Ubicacion) nativeQuery.getSingleResult();
	}
    

}
