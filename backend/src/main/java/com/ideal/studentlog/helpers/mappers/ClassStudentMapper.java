package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.ClassStudent;
import com.ideal.studentlog.helpers.dtos.ClassStudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassStudentMapper {
    ClassStudentMapper INSTANCE = Mappers.getMapper(ClassStudentMapper.class);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "classDetailsId", source = "classDetails.id")
    ClassStudentDTO classStudentToClassStudentDto(ClassStudent classStudent);

    void classStudentDtoToClassStudent(ClassStudentDTO dto,
                                      @MappingTarget ClassStudent classStudent);
}
