package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example4 {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        try (session) {

            session.beginTransaction();
            SessionStatePrinter.printState(session);

            Client gettedClient = session.get(Client.class, 1);
            System.out.println(gettedClient);

            SessionStatePrinter.printState(session);

            // УДАЛЯЕМ ЗАПИСЬ В БД. ПРИ ЭТОМ Java-ОБЪЕКТ ОСТАЕТСЯ В ЗОНЕ ВИДИМОСТИ Hibernate
            // И ПЕРЕХОДИТ В СОСТОЯНИЕ Removed С ТОЧКИ ЗРЕНИЯ Hibernate
            session.remove(gettedClient);

            SessionStatePrinter.printState(session);

            session.flush();

            // Java-ОБЪЕКТ ЕСТЬ Java-ОБЪЕКТ, ОН НИКУДА НЕ ДЕЛСЯ И ДОСТУПЕН ПОКА НА НЕГО ЕСТЬ ССЫЛКА
            System.out.println(gettedClient);

            gettedClient = null;

            session.getTransaction().commit();

        } catch (RuntimeException e) {
            session.getTransaction().rollback();
        }
    }
}