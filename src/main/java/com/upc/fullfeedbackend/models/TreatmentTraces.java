package com.upc.fullfeedbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "treatmentTrace")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TreatmentTraces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long treatmentTracesId;

    private Date day;

    private Byte success;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "personalTreatmentsId",nullable = false)
    private PersonalTreatments personalTreatments;


}
