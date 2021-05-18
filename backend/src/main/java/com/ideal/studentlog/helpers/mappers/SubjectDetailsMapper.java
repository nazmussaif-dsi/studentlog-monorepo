package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.SubjectDetails;
import com.ideal.studentlog.helpers.dataclass.SubjectDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectDetailsMapper {
    SubjectDetailsMapper INSTANCE = Mappers.getMapper(SubjectDetailsMapper.class);

    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "classDetailsId", source = "classDetails.id")
    SubjectDetailsDTO subjectDetailsToSubjectDetailsDto(SubjectDetails subjectDetails);

    void subjectDetailsDtoToSubjectDetails(SubjectDetailsDTO dto,
                                      @MappingTarget SubjectDetails subjectDetails);
}
