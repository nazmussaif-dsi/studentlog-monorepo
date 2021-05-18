package com.ideal.studentlog.helpers.dataclass;

import com.ideal.studentlog.helpers.validators.annotations.ValidStudentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class SaveAttendanceListDTO {

    @NotEmpty
    List<@Valid SaveAttendanceDTO> list;

    @NotNull
    Date date;
}
