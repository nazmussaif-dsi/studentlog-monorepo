package com.ideal.studentlog.helpers.dataclass;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class TestDTO {

    @NonNull
    @Schema(description = "Subject", example = "Bengali")
    private String subject;

    @NonNull
    private String examiner;

    @NonNull
    private Date date;

}
