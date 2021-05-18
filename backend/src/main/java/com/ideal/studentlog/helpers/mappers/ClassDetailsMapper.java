package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.ClassDetails;
import com.ideal.studentlog.helpers.dtos.ClassDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassDetailsMapper {
    ClassDetailsMapper INSTANCE = Mappers.getMapper(ClassDetailsMapper.class);

    @Mapping(target = "schoolClassId", source = "schoolClass.id")
    @Mapping(target = "teacherId", source = "teacher.id")
    ClassDetailsDTO classDetailsToClassDetailsDto(ClassDetails classDetails);

    void classDetailsDtoToClassDetails(ClassDetailsDTO dto,
                                      @MappingTarget ClassDetails classDetails);
}
