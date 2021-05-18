package com.ideal.studentlog.database.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    String adminId;

    @NonNull
    String name;

    @NonNull
    String designation;

    @NonNull
    Date dateOfBirth;

    String bloodGroup;

    @NonNull
    String highestEducationLevel;

    @NonNull
    Date joiningDate;

    Date resignationDate;

    @NonNull
    String contactNumber;

    @NonNull
    String presentAddress;

    @NonNull
    String permanentAddress;
}
