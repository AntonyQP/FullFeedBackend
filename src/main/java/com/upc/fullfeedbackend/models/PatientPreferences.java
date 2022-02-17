package com.upc.fullfeedbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "patientPreferences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long patientPreferencesId;


    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "preferencesId",nullable = true)
    private Preferences preferences;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId",nullable = true)
    private Patient patient;

    //ALLERGY - FAVORITE
    @Column(length = 25)
    private String type;

}
