package ru.soroko.unit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.soroko.Account;
import ru.soroko.AccountDao;
import ru.soroko.Application;
import ru.soroko.Transaction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionTest {
    @Test
    public void addMoney() throws InterruptedException {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("banking");
        EntityManager manager = factory.createEntityManager();
        int numberOfThreads = 2;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService service = Executors.newFixedThreadPool(10);
        AccountDao accountDao = new AccountDao(manager);
        Account account01 = new Account("client01", "qwerty123", 1337.0);
        Account account02 = new Account("client02", "qwerty123", 500.0);
        Transaction transaction01 = new Transaction(account01, account02, 400, accountDao);
        Thread thread = new Thread(transaction01);
        thread.start();
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                latch.countDown();
            });
        }
        latch.await();
        Assertions.assertEquals(900, account02.getBalance());
        Assertions.assertEquals(937, account01.getBalance());
    }


}
