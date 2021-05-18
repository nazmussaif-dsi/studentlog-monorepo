package com.ideal.studentlog.database.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;


import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class StudentApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    Date appliedDate;

    @ManyToOne
    Admin decidedBy;

    String name;

    Date dateOfBirth;

    String bloodGroup;

    String birthRegistrationId;

    String registrationId;

    String presentAddress;

    String permanentAddress;

    String guardianName;

    String guardianEmail;

    String guardianPhone;

    Integer appliedForGrade;

    @NonNull
    String status;

}
