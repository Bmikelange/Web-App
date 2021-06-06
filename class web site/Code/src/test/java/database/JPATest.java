package database;

import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Encryptor;
import fr.univlyon1.multimif_2019.projetgrp3.model.entity.Student;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class JPATest {
    /**
     * Check if the DataBase connection is established.
     */
    /*@Test
    public void DBConnetion() {
        EntityManager em = Persistence.createEntityManagerFactory("projet").createEntityManager();
        EntityManager em1 = Persistence.createEntityManagerFactory("projet").createEntityManager();
        EntityManager em2 = Persistence.createEntityManagerFactory("projet").createEntityManager();
        em.close();
        em1.close();
        em2.close();
    }*/
}
