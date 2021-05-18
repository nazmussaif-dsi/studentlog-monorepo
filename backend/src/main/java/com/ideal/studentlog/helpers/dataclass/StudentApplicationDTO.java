package com.ideal.studentlog.helpers.dataclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import com.ideal.studentlog.helpers.validators.annotations.ValidAdminId;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class StudentApplicationDTO implements Serializable {

    Integer id;

    @NonNull
    Date appliedDate;

    @ValidAdminId
    Integer decidedById;

    @Size(min = 5, max = 50)
    String name;

    Date dateOfBirth;

    @Pattern(regexp = "^(A|B|AB|O)[+-]$")
    String bloodGroup;

    @Size(min = 8, max = 20)
    String birthRegistrationId;

    @Size(min = 8, max = 20)
    String registrationId;

    @Size(min = 5, max = 100)
    String presentAddress;

    @Size(min = 5, max = 100)
    String permanentAddress;

    @Size(min = 5, max = 50)
    String guardianName;

    String guardianEmail;

    @Pattern(regexp = "^(\\+88)?01[0-9]{9}$")
    String guardianPhone;

    Integer appliedForGrade;

    @NonNull
    String status;
}
