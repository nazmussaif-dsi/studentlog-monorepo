package com.ideal.studentlog.helpers.dataclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class SubjectDTO {

    @NonNull
    @Size(min = 5, max = 50)
    String name;

    @NonNull
    @Size(min = 5, max = 50)
    String category;
}
