package org.example;

import org.example.entity.RegionEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example1 {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(RegionEntity.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // TRANSIENT
        RegionEntity regionEntity = new RegionEntity();

        try (session) {
            session.beginTransaction();
            SessionStatePrinter.printState(session);
            // PERSISTENT (MANAGED)
            regionEntity.setRegionDescription("regionDescription");
            session.persist(regionEntity);
            System.out.println(regionEntity);
            SessionStatePrinter.printState(session);
            session.flush();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
        }
    }


}