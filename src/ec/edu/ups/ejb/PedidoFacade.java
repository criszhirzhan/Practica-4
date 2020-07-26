package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.entidad.Pedido;


@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> {

	@PersistenceContext(unitName = "Practica-4")
    private EntityManager em;
	
	public PedidoFacade() {
		super(Pedido.class);
	}
	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Pedido getPedido(String cedula) {
    	Query nq = em.createNativeQuery("SELECT * FROM Pedido WHERE cliente_cedula=?", Pedido.class);
    	nq.setParameter(1, cedula);
    	return (Pedido) nq.getSingleResult();
    }

}
