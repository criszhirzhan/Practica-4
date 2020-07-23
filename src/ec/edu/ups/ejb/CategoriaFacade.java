package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.entidad.Categoria;

@Stateless
public class CategoriaFacade  extends AbstractFacade<Categoria>{

	@PersistenceContext(unitName = "Practica-4")
	private EntityManager em;
	
	
	public CategoriaFacade() {
		super(Categoria.class);
	}
	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @SuppressWarnings("unchecked")
	public List<Categoria> listarCategorias(){
    	Query nq = em.createNativeQuery("SELECT * FROM categoria", Categoria.class);
    	System.out.println("Obteniendo lista de categorias");
    	System.out.println("Lista de categorias: "+nq.getResultList());
    	return (List<Categoria>) nq.getResultList();
    }	
    
    public Categoria obtenerCategoria(String categoria) {
    	Query nq = em.createNativeQuery("SELECT * FROM categoria WHERE nombre= ?", Categoria.class);
    	nq.setParameter(1, categoria);
    	System.out.println("Obteniendo categoria desde categoriafacade");
    	System.out.println("Categoria obtenido: "+nq.getSingleResult());
    	return (Categoria) nq.getSingleResult();
    }
}
