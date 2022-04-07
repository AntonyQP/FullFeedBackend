package com.upc.fullfeedbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "personalTreatment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalTreatments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long personalTreatmentId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId",nullable = false)
    private Patient patient;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId",nullable = false)
    private Doctor doctor;

    private LocalDate startDate;

    private LocalDate endDate;

    private Byte active;

}
