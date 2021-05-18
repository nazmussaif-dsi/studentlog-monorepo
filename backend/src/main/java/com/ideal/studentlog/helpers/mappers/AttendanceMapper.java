package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.Attendance;
import com.ideal.studentlog.helpers.dataclass.AttendanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "studentId", source = "student.id")
    AttendanceDTO attendanceToAttendanceDto(Attendance attendance);

    void attendanceDtoToAttendance(AttendanceDTO dto,
                                      @MappingTarget Attendance attendance);
}
