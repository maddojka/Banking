package ru.soroko;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_clients")
public class Client {
    @Id
    @GeneratedValue
    private long id;

    @Positive
    @Column(name = "phoneNumber", unique = true, nullable = false)
    private long phoneNumber;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 50)
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 50)
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @JoinColumn(name = "bank_account_id", unique = true, nullable = false)
    private BankAccount bankAccount;
}
