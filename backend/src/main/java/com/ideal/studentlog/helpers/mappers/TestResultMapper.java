package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.TestResult;
import com.ideal.studentlog.helpers.dataclass.TestResultDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestResultMapper {
    TestResultMapper INSTANCE = Mappers.getMapper(TestResultMapper.class);

    @Mapping(target = "testId", source = "test.id")
    @Mapping(target = "studentId", source = "student.id")
    TestResultDTO testResultToTestResultDto(TestResult testResult);

    void testResultDtoToTestResult(TestResultDTO dto,
                                      @MappingTarget TestResult testResult);
}
