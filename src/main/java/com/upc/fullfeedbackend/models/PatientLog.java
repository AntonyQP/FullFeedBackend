package com.upc.fullfeedbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patientLog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long patientLogId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId",nullable = false)
    private Patient patient;

    private float height;
    private float weight;
    private float imc;
    private float arm;
    private float abdominal;
    private float tmb;
    private Date date;


}
