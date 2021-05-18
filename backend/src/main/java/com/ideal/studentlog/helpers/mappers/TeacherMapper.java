package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.helpers.dataclass.TeacherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherDTO teacherToTeacherDto(Teacher teacher);
    void teacherDtoToTeacher(TeacherDTO dto, @MappingTarget Teacher teacher);
}
