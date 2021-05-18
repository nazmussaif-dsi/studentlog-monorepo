package com.ideal.studentlog.helpers.dataclass;

import com.ideal.studentlog.helpers.validators.annotations.ValidStudentId;
import com.ideal.studentlog.helpers.validators.annotations.ValidTeacherId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
@Data
@AllArgsConstructor
public class AttendanceDTO {

    @NonNull
    Date date;

    @NonNull
    @ValidStudentId
    Integer studentId;

    @NonNull
    @ValidTeacherId
    Integer teacherId;

    @NonNull
    Boolean isPresent;
}
