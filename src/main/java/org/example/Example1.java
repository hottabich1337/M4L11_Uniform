package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example1 {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // TRANSIENT
        Client client = new Client("Yura", "S");

        try (session) {
            session.beginTransaction();
            SessionStatePrinter.printState(session);
            // PERSISTENT (MANAGED)
            session.persist(client);
            System.out.println(client);
            SessionStatePrinter.printState(session);
            session.flush();
        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
        }
    }


}