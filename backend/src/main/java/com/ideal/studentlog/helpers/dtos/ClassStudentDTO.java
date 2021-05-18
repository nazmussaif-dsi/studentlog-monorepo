package com.ideal.studentlog.helpers.dtos;

import com.ideal.studentlog.helpers.validators.annotations.ValidStudentId;
import com.ideal.studentlog.helpers.validators.annotations.ValidClassDetailsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ClassStudentDTO {
    @NonNull
    @ValidClassDetailsId
    Integer classDetailsId;

    @NonNull
    @ValidStudentId
    Integer studentId;
}
