package com.upc.fullfeedbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(length = 250)
    private String username;

    @Column(length = 100)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(length = 250)
    private String firstName;

    @Column(length = 250)
    private String lastName;

    private Date birthDate;

    private String phone;

    @Column(length = 1, nullable = false)
    private String sex;

    @Column(length = 8)
    private String dni;

    private Date registerDate;

    @Column(length = 1, nullable = false)
    private String rol;



}
