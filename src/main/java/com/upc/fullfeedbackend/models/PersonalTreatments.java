package com.upc.fullfeedbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JoinColumn(name = "patienceId",nullable = false)
    private Patience patience;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId",nullable = false)
    private Doctor doctor;

    private Date startDate;

    private Date endDate;

    private Byte active;



}
