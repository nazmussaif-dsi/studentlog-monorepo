package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.StudentApplication;
import com.ideal.studentlog.helpers.dataclass.StudentApplicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentApplicationMapper {
    StudentApplicationMapper INSTANCE = Mappers.getMapper(StudentApplicationMapper.class);

    @Mapping(target = "decidedById", source = "decidedBy.id")
    StudentApplicationDTO studentApplicationToStudentApplicationDto(StudentApplication studentApplication);
    void studentApplicationDtoToStudentApplication(StudentApplicationDTO dto,
                                      @MappingTarget StudentApplication studentApplication);
}
