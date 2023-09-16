package com.springbot.tttn.application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "QTV")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "maQTV")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "matKhau")
    private String password;

    @Column(name = "tenQTV")
    private String fullName;
}