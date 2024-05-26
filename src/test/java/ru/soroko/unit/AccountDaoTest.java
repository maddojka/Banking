package ru.soroko.unit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.soroko.Account;
import ru.soroko.AccountDao;

public class AccountDaoTest {
    private AccountDao accountDao;

    @BeforeEach
    void setUp() {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("banking");
        EntityManager manager = factory.createEntityManager();
        accountDao = new AccountDao(manager);
    }

    @Test
    public void setBalance_negativeBalance_throwsException() {
        Account account01 = new Account("client01", "qwerty123", 5.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> account01.setBalance(-1.0));
    }

    @Test
    public void insert_nullcheck_throwsNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> accountDao.insert(null));
    }

    @Test
    public void update_nullcheck_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountDao.update(null));
    }

    @Test
    public void deleteById_nullcheck_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountDao.deleteById(null));
    }

    @Test
    public void findById_nullcheck_throwsExceprtion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountDao.findById(null));
    }
}
