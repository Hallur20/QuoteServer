/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Cookie;
import Entities.Quoting;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Quoting> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Quoting> l = em.createQuery("SELECT p FROM Quoting p").getResultList();

            return l;
        } finally {
            em.close();
        }
    }

    public void edit(String newQuote) {
        EntityManager em = emf.createEntityManager();
        try {
            Quoting q = em.find(Quoting.class, 1);
            em.getTransaction().begin();
            q.setQuote(newQuote);
            em.getTransaction().commit();
        } catch (Exception ex) {

        } finally {
            em.close();
        }
    }

    public int addToCookie() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Integer> list = em.createNamedQuery("Cookie.findByAmount").getResultList();
   
            int result = list.get(0)+1;
            Cookie c = em.find(Cookie.class, 1);
            c.setAmount(result);
            em.getTransaction().commit();
            return result;
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        Factory f = new Factory();
        f.addEntityManagerFactory(emf);
        f.addToCookie();
    }
}
