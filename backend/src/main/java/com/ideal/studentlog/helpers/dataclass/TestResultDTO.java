package com.ideal.studentlog.helpers.dataclass;

import com.ideal.studentlog.helpers.validators.annotations.ValidStudentId;
import com.ideal.studentlog.helpers.validators.annotations.ValidTestId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TestResultDTO {

    @NonNull
    @ValidTestId
    Integer testId;

    @NonNull
    @ValidStudentId
    Integer studentId;

    @NonNull
    String grade;

}
