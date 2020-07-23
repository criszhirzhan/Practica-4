package ec.edu.ups.ejb;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import ec.edu.ups.entidad.FacturaDetalle;

@Stateless
public class FacturaDetalleFacade extends AbstractFacade<FacturaDetalle>{

	 @PersistenceContext(unitName = "Practica-4")
	    private EntityManager em;
	
	public FacturaDetalleFacade() {
		super(FacturaDetalle.class);
	}
	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

	public List<FacturaDetalle> listarFacturaDetalle(int codigo) {
		Query nativeQuery = em.createNativeQuery("SELECT * FROM facturadetalle WHERE FACTURA_CODIGOFACTURA =? ", FacturaDetalle.class);
		nativeQuery.setParameter(1, codigo);

		return (List<FacturaDetalle>) nativeQuery.getResultList();
	}

}
