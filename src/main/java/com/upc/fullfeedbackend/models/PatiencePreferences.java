package com.upc.fullfeedbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "patiencePreferences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatiencePreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long patiencePreferencesId;


    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "preferencesId",nullable = false)
    private Preferences preferences;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "patienceId",nullable = false)
    private Patience patience;

    //ALLERGY - FAVORITE
    @Column(length = 25)
    private String type;

}
