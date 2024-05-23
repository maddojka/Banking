package ru.soroko;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class ClientDao extends Dao<Client, Long> {
    @NotNull
    private final HashSet<String> emails = new HashSet<>();

    @NotNull
    private final HashSet<Long> phoneNumbers = new HashSet<>();

    @NotNull
    private final HashSet<String> logins = new HashSet<>();

    public ClientDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Long insert(Client client) {
        entityManager.getTransaction().begin();
        if (emails.contains(client.getEmail()) || phoneNumbers.contains(client.getPhoneNumber())
                || logins.contains(client.getAccount().getLogin())) {
            System.out.println("Пользователь с такими данными уже существует");
            return null;
        } else {
            emails.add(client.getEmail());
            phoneNumbers.add(client.getPhoneNumber());
            logins.add(client.getAccount().getLogin());
            entityManager.persist(client);
        }
        entityManager.getTransaction().commit();
        return client.getId();
    }

    @Override
    public void update(Client client) {
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
    }

    @Override
    public Client deleteById(Long longVal) {
        Client client = entityManager.find(Client.class, longVal);
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
        return client;
    }

    @Override
    public Client findById(Long longVal) {
        return entityManager.find(Client.class, longVal);
    }

    public Client getByDateOfBirth(LocalDate date) {
        String getByDateOfBirthSql = "SELECT * " +
                "FROM tb_clients " +
                "WHERE  date_of_birth >:date " +
                "ORDER BY full_name ";
        Query query = entityManager.createNativeQuery(getByDateOfBirthSql, Client.class);
        return (Client) query.getSingleResult();
    }

    public Client getByPhoneNumber(Long phoneNumber) {
        String getByPhoneNumberSql = "SELECT * " +
                "FROM tb_clients " +
                "WHERE  phone_number =:phoneNumber " +
                "ORDER BY full_name ";
        Query query = entityManager.createNativeQuery(getByPhoneNumberSql, Client.class);
        return (Client) query.getSingleResult();
    }

    public List<Client> getFullName(String fullName) {
        String getByPhoneNumberSql = "SELECT * " +
                "FROM tb_clients " +
                "WHERE  full_name =:full_name " +
                "ORDER BY full_name ";
        Query query = entityManager.createNativeQuery(getByPhoneNumberSql, Client.class);
        return query.getResultList();
    }

    public Tuple getByEmail(String email) {
        String getByEmailSql = "SELECT full_name, email " +
                "FROM tb_clients " +
                "WHERE  email =:email " +
                "ORDER BY email ";
        Query query = entityManager.createNativeQuery(getByEmailSql, Tuple.class);
        return (Tuple) query.getSingleResult();
    }
}
