package ru.soroko;

import jakarta.persistence.EntityManager;

public class AccountDao extends Dao<Account, Long> {

    public AccountDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Long insert(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        return account.getId();
    }

    @Override
    public void update(Account account) {
        entityManager.getTransaction().begin();
        entityManager.merge(account);
        entityManager.getTransaction().commit();
    }

    @Override
    public Account deleteById(Long longVal) {
        Account account = entityManager.find(Account.class, longVal);
        entityManager.getTransaction().begin();
        entityManager.remove(account);
        entityManager.getTransaction().commit();
        return account;
    }

    @Override
    public Account findById(Long longVal) {
        return entityManager.find(Account.class, longVal);
    }
}
