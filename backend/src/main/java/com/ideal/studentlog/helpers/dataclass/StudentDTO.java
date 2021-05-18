package com.ideal.studentlog.helpers.dataclass;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class StudentDTO {

    Integer id;

    @NotNull
    @Size(min = 5, max = 50)
    String name;

    @NotNull
    @Size(min = 8, max = 20)
    String birthRegistrationId;

    @NotNull
    @Size(min = 8, max = 20)
    String studentId;

    @NotNull
    Date dateOfBirth;

    @NotNull
    @Pattern(regexp = "^(A|B|AB|O)[+-]$")
    String bloodGroup;

    @NotNull
    @Size(min = 5, max = 100)
    String presentAddress;

    @NotNull
    @Size(min = 5, max = 100)
    String permanentAddress;

    @NotNull
    @Size(min = 5, max = 50)
    String guardianName;

    @Email
    String guardianEmail;

    @NotNull
    @Pattern(regexp = "^(\\+88)?01[0-9]{9}$")
    String guardianPhone;

}
