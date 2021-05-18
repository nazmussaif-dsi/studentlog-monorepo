package com.ideal.studentlog.helpers.dataclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {

    @NonNull
    String error;

    @NonNull
    String message;

    @NonNull
    String code;

}
