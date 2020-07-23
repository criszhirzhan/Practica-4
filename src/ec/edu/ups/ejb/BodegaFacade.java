package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.entidad.Bodega;
import ec.edu.ups.entidad.Producto;
import ec.edu.ups.entidad.Usuario;


@Stateless
public class BodegaFacade  extends AbstractFacade<Bodega> {


    @PersistenceContext(unitName = "Practica-4")
    private EntityManager em;
	
	public BodegaFacade() {
		super(Bodega.class);
	}
	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @SuppressWarnings("unchecked")
	public List<Bodega> listarBodegas(){
    	Query nq = em.createNativeQuery("SELECT * FROM bodega", Bodega.class);
    	System.out.println("Listando bodega");
    	System.out.println("Lista desde Bodegafacade"+nq.getResultList());
    	return (List<Bodega>) nq.getResultList();
    }
    
    public Bodega obtenerBodegas(String bodega) {
    	Query nq = em.createNativeQuery("SELECT * FROM bodega WHERE nombre= ?", Bodega.class);
    	nq.setParameter(1, bodega);
    	System.out.println("Obteniendo bodega");
    	System.out.println("Bodega obtenido: "+nq.getSingleResult());
    	return (Bodega) nq.getSingleResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<Usuario> obtenerAdministradores(){
    	Query nq = em.createNativeQuery("SELECT * FROM Usuario WHERE rol_codigo=3", Usuario.class);
    	System.out.println("retornando consulta desde facade");
    	return (List<Usuario>) nq.getResultList();
    }
    
    public List<Bodega> obtenerBodegas(int id) {
    	Query nq = em.createNativeQuery("SELECT * FROM bodega WHERE nombre= ?", Bodega.class);
    	nq.setParameter(1, id);

    	return (List<Bodega>) nq.getSingleResult();
    }
    
}
