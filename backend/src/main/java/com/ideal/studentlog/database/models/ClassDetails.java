package com.ideal.studentlog.database.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ClassDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    @ManyToOne
    SchoolClass schoolClass;

    @NonNull
    String year;

    @NonNull
    @ManyToOne
    Teacher teacher;
}
