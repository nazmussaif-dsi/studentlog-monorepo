package com.ideal.studentlog.database.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class SubjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    @OneToOne
    Subject subject;

    @NonNull
    @ManyToOne
    Teacher teacher;

    @NonNull
    @ManyToOne
    ClassDetails classDetails;
}
