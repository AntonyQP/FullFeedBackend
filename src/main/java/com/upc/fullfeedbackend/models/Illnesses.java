package com.upc.fullfeedbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "illnesses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Illnesses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long illnessesId;

    @Column(length = 100)
    private String name;

    @Column(length = 250)
    private String description;

}
