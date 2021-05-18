package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.Test;
import com.ideal.studentlog.helpers.dataclass.TestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestMapper {
    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    TestDTO testToTestDto(Test test);
    void testDtoToTest(TestDTO dto, @MappingTarget Test test);
}
