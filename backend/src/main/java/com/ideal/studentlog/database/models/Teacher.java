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
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    String name;

    @NonNull
    Date dateOfBirth;

    @NonNull
    Date joiningDate;

    @NonNull
    Date resignationDate;

    @NonNull
    String highestEducationLevel;

    @NonNull
    String nationalRegistrationNo;

    @NonNull
    String teacherId;

    @NonNull
    String designation;

    @NonNull
    String contactNo;

    @NonNull
    String presentAddress;

    @NonNull
    String permanentAddress;

    @NonNull
    String bloodGroup;
}
