package com.upc.fullfeedbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "patience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long patienceId;

    private float height;
    private float weight;
    private float imc;
    private float arm;
    private float abdominal;
    private float tmb;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",nullable = true)
    private User user;

}
