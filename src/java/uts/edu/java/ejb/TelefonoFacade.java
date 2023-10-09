/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.edu.java.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import uts.edu.java.modelo.Telefono;

/**
 *
 * @author cuent
 */
@Stateless
public class TelefonoFacade extends AbstractFacade<Telefono> {

    @PersistenceContext(unitName = "AgendaJPAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelefonoFacade() {
        super(Telefono.class);
    }
    
    
    public int obtenerUltimoId() {
        String sql = "SELECT MAX(id) FROM Telefono";
        Query query = em.createNativeQuery(sql);
        Object result = query.getSingleResult();
        if (result != null) {
            return (int) result;
        }
        return 0; // Si no hay registros en la tabla
    }
    
        public Telefono searchTelefono(int id){
        em.flush();
        
        return em.find(Telefono.class, id);
    }
        
            public void removeTelefono(int id){
        em.remove(searchTelefono(id));
        em.flush();
    }
            public List<Telefono> obtenerTelefonosDePersona(String rut) {
        TypedQuery<Telefono> query = em.createQuery(
            "SELECT t FROM Telefono t WHERE t.rut.rut = :rut", Telefono.class);
        query.setParameter("rut", rut);
        return query.getResultList();}
            
            
    
}
