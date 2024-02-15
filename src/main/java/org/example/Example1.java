package org.example;

import org.example.entity.Table1Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example1 {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Table1Entity.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // TRANSIENT
        Table1Entity table1Entity = new Table1Entity();

        try (session) {
            session.beginTransaction();
            SessionStatePrinter.printState(session);
            // PERSISTENT (MANAGED)
            table1Entity.setName("Name");
            session.persist(table1Entity);
            System.out.println(table1Entity);
            SessionStatePrinter.printState(session);
            session.flush();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
        }
    }


}