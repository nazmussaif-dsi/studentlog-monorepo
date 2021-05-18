package com.ideal.studentlog.helpers.dtos;

import com.ideal.studentlog.database.models.SchoolClass;
import com.ideal.studentlog.helpers.validators.annotations.ValidTeacherId;
import com.ideal.studentlog.helpers.validators.annotations.ValidSchoolClassId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ClassDetailsDTO {
    @NonNull
    @ValidSchoolClassId
    Integer schoolClassId;

    @NonNull
    String year;

    @NonNull
    @ValidTeacherId
    Integer teacherId;
}
