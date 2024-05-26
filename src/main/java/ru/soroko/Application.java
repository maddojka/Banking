package ru.soroko;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("banking");
        EntityManager manager = factory.createEntityManager();
        ClientDao clientDao = new ClientDao(manager);
        AccountDao accountDao = new AccountDao(manager);
        Account account01 = new Account("client01", "qwerty123", 1337.0);
        Client client01 = new Client("Adam Smith", 892113374563L, "asmith@gmail.com",
                LocalDate.of(1962, 8, 6), account01);
        clientDao.insert(/*client01*/null);
        Account account02 = new Account("client02", "qwerty123", 500.0);
        Client client02 = new Client("Samuel Adamson", 892187374572L, "sadamson@gmail.com",
                LocalDate.of(1957, 11, 13), account02);
        clientDao.insert(client02);
        Account account03 = new Account("client03", "qwerty123", 700.0);
        Client client03 = new Client("Olivia Smith", 891178855541L, "osmith@gmail.com",
                LocalDate.of(1989, 6, 18), account03);
        clientDao.insert(client03);
        Account account04 = new Account("client04", "qwerty123", 2000.0);
        Client client04 = new Client("Jack Aldridge", 893144579005L, "jaldridge@gmail.com",
                LocalDate.of(1994, 1, 9), account04);
        clientDao.insert(client04);
        Account account05 = new Account("client05", "qwerty123", 1000.0);
        Client client05 = new Client("Amelia Brown", 892115576522L, "abrown@gmail.com",
                LocalDate.of(1985, 4, 15), account05);
        clientDao.insert(client05);
        Transaction transaction01 = new Transaction(account01, account02, 400, accountDao);
        Thread thread = new Thread(transaction01);
        thread.start();
    }
}
