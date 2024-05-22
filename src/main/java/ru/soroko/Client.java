package ru.soroko;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull
    @NotBlank
    @Size(min = 10, max = 50)
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Positive
    @Column(name = "phoneNumber", nullable = false)
    private long phoneNumber;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotNull
    @Past
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @OneToOne
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    public Client(String fullName, long phoneNumber, String email, LocalDate dateOfBirth, Account account) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
    }
}
