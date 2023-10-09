/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.edu.java.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uts.edu.java.modelo.Categoria;

/**
 *
 * @author cuent
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "AgendaJPAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
        public void removeCategoria(int id){
        em.remove(buscaCategoria(id));
        em.flush();
    }
    public Categoria buscaCategoria(int id){
        em.flush();
        
        return em.find(Categoria.class, id);
    }
}
