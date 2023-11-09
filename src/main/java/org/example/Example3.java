package org.example;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example3 {
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

            gettedClient.setFirstName("Ivan");

            session.persist(gettedClient);
            session.save(gettedClient);

            SessionStatePrinter.printState(session);

            System.out.println(gettedClient);

            // ЗАКРЫВАЕМ СЕССИЮ - ПОСЛЕ ЭТОГО СТАТИСТИКУ ПО НЕЙ БУДЕТ НЕ СОБРАТЬ (СМ. СТРОКУ 42)
            session.close();

            // ОЧИЩАЕМ СЕССИЮ - ПОСЛЕ ЭТОГО ИЗМЕНЕНИЯ ПРОИЗВЕДЕННЫЕ С ОБЪЕКТАМИ В НЕЙ - СТИРАЮТСЯ
            // session.clear();

            /*
               МЕТОД flush() ТРАНСЛИРУЕТ ИЗМЕНЕНИЯ ОТСЛЕЖИВАЕМЫХ СУЩНОСТЕЙ В БАЗУ ДАННЫХ,
               ТО ЕСТЬ ВЫПОЛНЯЕТ НАКОПИВШИЕСЯ SQL-КОМАНДЫ
            */
            // session.flush();

            SessionStatePrinter.printState(session);

        } catch (RuntimeException e) {
            session.getTransaction().rollback();
        }
    }
}