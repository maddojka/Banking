package ru.soroko;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Timer;
import java.util.TimerTask;

@Entity
@Table(name = "ACCOUNTS")
@NoArgsConstructor
public class Account {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 10, max = 50)
    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @Size(min = 10, max = 50)
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @PositiveOrZero
    @Column(name = "balance", nullable = false)
    private double balance;

    @Getter
    private double balanceSetpoint;

    public Account(String login, String password, double balance) {
        setBalance(balance);
        this.login = login;
        this.password = password;
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double balance) {
        if (balance < 0) throw new IllegalArgumentException("Баланс не может быть отрицательным");
        this.balance = balance;
    }

    public synchronized void increaseBalance() {
        Timer timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            public void run() {
                if (balance <= balanceSetpoint) {
                    System.out.println(balance);
                    balance += balance * 0.05;
                } else {
                    balance = balanceSetpoint;
                    timer.cancel();
                }
            }
        };

        long delay = 1000L;
        long period = 1000L;
        balanceSetpoint = balance * 2.07;
        timer.scheduleAtFixedRate(task, delay, period);
    }

}
