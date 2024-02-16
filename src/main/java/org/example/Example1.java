package org.example;

import org.example.entity.TableEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example1 {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(TableEntity.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // TRANSIENT
        TableEntity tableEntity = new TableEntity();

        try (session) {
            session.beginTransaction();
            SessionStatePrinter.printState(session);
            // PERSISTENT (MANAGED)
            tableEntity.setName("Name");
            session.persist(tableEntity);
            System.out.println(tableEntity);
            SessionStatePrinter.printState(session);
            session.flush();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
        }
    }


}