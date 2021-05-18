package com.ideal.studentlog.database.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    String name;

    @NonNull
    String birthRegistrationId;

    @NonNull
    String studentId;

    @NonNull
    Date dateOfBirth;

    @NonNull
    String bloodGroup;

    @NonNull
    String presentAddress;

    @NonNull
    String permanentAddress;

    @NonNull
    String guardianName;

    String guardianEmail;

    @NonNull
    String guardianPhone;

}
