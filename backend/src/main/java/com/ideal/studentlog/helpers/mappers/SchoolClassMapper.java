package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.SchoolClass;
import com.ideal.studentlog.helpers.dtos.SchoolClassDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchoolClassMapper {
    SchoolClassMapper INSTANCE = Mappers.getMapper(SchoolClassMapper.class);


    SchoolClassDTO schoolClassToSchoolClassDto(SchoolClass schoolClass);
    void schoolClassDtoToSchoolClass(SchoolClassDTO dto, @MappingTarget SchoolClass schoolClass);
}
