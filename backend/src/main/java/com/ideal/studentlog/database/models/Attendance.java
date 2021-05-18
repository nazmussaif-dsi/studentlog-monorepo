package com.ideal.studentlog.database.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    Date date;

    @NonNull
    @ManyToOne
    Student student;

    @NonNull
    @ManyToOne
    Teacher teacher;

    @NonNull
    Boolean isPresent;
}
