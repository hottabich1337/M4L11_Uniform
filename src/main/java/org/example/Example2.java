package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example2 {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try (session) {

            SessionStatePrinter.printState(session);

            // PERSISTENT (MANAGED)
            Client gettedClient = session.get(Client.class, 1);
            System.out.println(gettedClient);

            System.out.println("-".repeat(100));

            Client loadedClient = session.load(Client.class, 2);
            System.out.println(loadedClient);

            System.out.println("-".repeat(100));

            Client findedClient = session.find(Client.class, 3);
            System.out.println(findedClient);

            SessionStatePrinter.printState(session);

        } catch (RuntimeException e) {
            session.getTransaction().rollback();
        }
    }
}