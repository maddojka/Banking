package ru.soroko;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
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

    @PositiveOrZero
    private long balance;

    @Setter
    @Getter
    @OneToOne
    private Client client;

    public synchronized long getBalance() {
        return balance;
    }

    public synchronized void setBalance(long balance) {
        this.balance = balance;
    }

}
