package com.ideal.studentlog.helpers.dataclass;


import com.ideal.studentlog.helpers.validators.annotations.ValidClassDetailsId;
import com.ideal.studentlog.helpers.validators.annotations.ValidTeacherId;
import com.ideal.studentlog.helpers.validators.annotations.ValidSubjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class SubjectDetailsDTO {
    
    @NonNull
    @ValidSubjectId
    Integer subjectId;

    @NonNull
    @ValidTeacherId
    Integer teacherId;

    @NonNull
    @ValidClassDetailsId
    Integer classDetailsId;

}
