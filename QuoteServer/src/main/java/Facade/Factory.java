/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Quoting;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author hvn15
 */
public class Factory {
        EntityManagerFactory emf;

    public Factory() {
    }
    
    

    public Factory(EntityManagerFactory emf) {
        
    }
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }
    
    public List<Quoting> getAll(){
        EntityManager em = emf.createEntityManager();
        try{
            List<Quoting> l = em.createQuery("SELECT p FROM Quoting p").getResultList();

            return l;
        } catch(Exception ex){
            
        } finally{
            
        } return null;
    }
    public void edit(String newQuote){
        EntityManager em = emf.createEntityManager();
        try{
            Quoting q = em.find(Quoting.class, 1);
            em.getTransaction().begin();
            q.setQuote(newQuote);
            em.getTransaction().commit();
        } catch(Exception ex){
            
        } finally{
            
        }
    }
}
