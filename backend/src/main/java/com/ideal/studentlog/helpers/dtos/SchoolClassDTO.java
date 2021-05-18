package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class SchoolClassDTO {
    @NonNull
    @Size(min = 1, max = 20)
    String name;

    @NonNull
    Integer grade;
}
