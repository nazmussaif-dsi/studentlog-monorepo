package com.ideal.studentlog.helpers.dataclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class TeacherDTO {

    @NonNull
    @Size(min = 5, max = 50)
    String name;

    @NonNull
    Date dateOfBirth;

    @NonNull
    Date joiningDate;

    @NonNull
    Date resignationDate;

    @NonNull
    String highestEducationLevel;

    @NonNull
    @Size(min = 8, max = 20)
    String nationalRegistrationNo;

    @NonNull
    @Size(min = 4, max = 20)
    String teacherId;

    @NonNull
    @Size(max = 30)
    String designation;

    @NonNull
    String contactNo;

    @NonNull
    @Size(max = 100)
    String presentAddress;

    @NonNull
    @Size(max = 100)
    String permanentAddress;

    @NonNull
    @Pattern(regexp = "^(A|B|AB|O)[+-]$")
    String bloodGroup;
}
