package com.upc.fullfeedbackend.models.dto;

import lombok.*;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ConsumedBalanceResponseDTO {
        private Date date;
        private double fat;
        private double carbohydrates;
        private double protein;
}