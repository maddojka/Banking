package ru.soroko.unit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.soroko.ClientDao;

public class ClientDaoTest {
    private ClientDao clientDao;

    @BeforeEach
    void setUp() {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("banking");
        EntityManager manager = factory.createEntityManager();
        clientDao = new ClientDao(manager);
    }

    @Test
    public void insert_nullcheck_throwsNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> clientDao.insert(null));
    }

    @Test
    public void update_nullcheck_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> clientDao.update(null));
    }

    @Test
    public void deleteById_nullcheck_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> clientDao.deleteById(null));
    }

    @Test
    public void findById_nullcheck_throwsExceprtion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> clientDao.findById(null));
    }

    @Test
    public void getByDateOfBirth() {
    }

    @Test
    public void getByPhoneNumber() {
    }

    @Test
    public void getFullName() {
    }

    @Test
    public void getByEmail() {
    }

}
