package com.ideal.studentlog.helpers.dataclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class AdminDTO {

    @NonNull
    @Size(min = 5, max = 50)
    String adminId;

    @NonNull
    @Size(min = 5, max = 50)
    String name;

    @NonNull
    @Size(min = 5, max = 50)
    String designation;

    @NonNull
    Date dateOfBirth;

    @NonNull
    @Pattern(regexp = "^(A|B|AB|O)[+-]$")
    String bloodGroup;

    @NonNull
    @Size(min = 5, max = 50)
    String highestEducationLevel;

    @NonNull
    Date joiningDate;

    Date resignationDate;

    @NonNull
    @Pattern(regexp = "^(\\+88)?01[0-9]{9}$")
    String contactNumber;

    @NonNull
    @Size(min = 5, max = 100)
    String presentAddress;

    @NonNull
    @Size(min = 5, max = 100)
    String permanentAddress;
}
