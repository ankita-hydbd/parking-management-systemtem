package com.example.parkingmanagementsystem.adminAccount;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
