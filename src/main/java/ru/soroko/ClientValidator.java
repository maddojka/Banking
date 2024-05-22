package ru.soroko;

import jakarta.validation.constraints.NotNull;

import java.util.HashSet;


public class ClientValidator {
    @NotNull
    private final HashSet<String> emails = new HashSet<>();

    @NotNull
    private final HashSet<Long> phoneNumbers = new HashSet<>();

    @NotNull
    private final HashSet<String> logins = new HashSet<>();

    @NotNull
    ClientDao clientDao;

    public void addClient(Client client) {
        if (emails.contains(client.getEmail()) || phoneNumbers.contains(client.getPhoneNumber())
        || logins.contains(client.getAccount().getLogin())) {
            System.out.println("Пользователь с такими данными уже существует");
        } else {
            emails.add(client.getEmail());
            phoneNumbers.add(client.getPhoneNumber());
            logins.add(client.getAccount().getLogin());
            clientDao.insert(client);
        }
    }
}
