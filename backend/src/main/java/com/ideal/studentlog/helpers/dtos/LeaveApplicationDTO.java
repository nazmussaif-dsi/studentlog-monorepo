package com.ideal.studentlog.helpers.dtos;

import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.helpers.validators.annotations.ValidTeacherId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class LeaveApplicationDTO {

    @NonNull
    Date dateFrom;

    @NonNull
    Date dateTo;

    @NonNull
    Integer studentId;

    @NonNull
    @Size(max = 1000)
    String applicationBody;

    @NonNull
    @ValidTeacherId
    Integer approvedById;
}
