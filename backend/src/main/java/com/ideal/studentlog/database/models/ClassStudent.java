package com.ideal.studentlog.database.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    @ManyToOne
    ClassDetails classDetails;

    @NonNull
    @ManyToOne
    Student student;
}
