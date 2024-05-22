package ru.soroko;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_accounts")
public class Account {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 10, max = 50)
    @Column(name = "full_name", nullable = false, length = 50)
    private String login;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 10, max = 50)
    @Column(name = "full_name", nullable = false, length = 50)
    private String password;

    @PositiveOrZero
    private double balance;

    public Account(String login, String password, double balance) {
        this.login = login;
        this.password = password;
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }

}
