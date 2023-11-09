package org.example;

import org.hibernate.Session;

public class SessionStatePrinter {

    public static void printState(Session session){
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.println(ANSI_BLUE + ">".repeat(50));
        System.out.printf("Session: %s%n" +
                        "Is dirty: %s%n" +
                        "Stat: %s%n",
                session,
                session.isDirty(),
                session.getStatistics());
        System.out.println(">".repeat(50) + ANSI_RESET);
    }

}
