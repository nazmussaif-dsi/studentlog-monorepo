package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.Subject;
import com.ideal.studentlog.helpers.dataclass.SubjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDTO subjectToSubjectDto(Subject subject);
    void subjectDtoToSubject(SubjectDTO dto, @MappingTarget Subject subject);
}
