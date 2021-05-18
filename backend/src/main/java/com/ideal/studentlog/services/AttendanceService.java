package com.ideal.studentlog.services;

import com.ideal.studentlog.helpers.dataclass.SaveAttendanceDTO;
import com.ideal.studentlog.helpers.dataclass.SaveAttendanceListDTO;
import com.ideal.studentlog.helpers.mappers.AttendanceMapper;
import liquibase.pro.packaged.D;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.database.models.Attendance;
import com.ideal.studentlog.helpers.dataclass.AttendanceDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.database.repositories.AttendanceRepository;

import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private static final AttendanceMapper mapper = AttendanceMapper.INSTANCE;

    private final AttendanceRepository repository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    public List<AttendanceDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::attendanceToAttendanceDto)
                .collect(Collectors.toList());
    }

    public AttendanceDTO getById(Integer id) throws ServiceException {
        return mapper.attendanceToAttendanceDto(getAttendance(id));
    }

    @Transactional
    public AttendanceDTO create(AttendanceDTO dto) throws ServiceException {
        Attendance attendance = new Attendance();

        mapper.attendanceDtoToAttendance(dto, attendance);
        attendance.setStudent(getStudent(dto.getStudentId()));
        attendance.setTeacher(getTeacher(dto.getTeacherId()));

        return mapper.attendanceToAttendanceDto(repository.save(attendance));
    }

    @Transactional
    public AttendanceDTO update(Integer id, AttendanceDTO dto) throws ServiceException {
        Attendance attendance = getAttendance(id);

        mapper.attendanceDtoToAttendance(dto, attendance);
        attendance.setStudent(getStudent(dto.getStudentId()));
        attendance.setTeacher(getTeacher(dto.getTeacherId()));

        return mapper.attendanceToAttendanceDto(repository.save(attendance));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Attendance getAttendance(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Attendance not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private Student getStudent(@NonNull Integer id) throws ServiceException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Student not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    private Teacher getTeacher(@NonNull Integer id) throws ServiceException {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Student not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    public void save(SaveAttendanceListDTO listDTO, Integer teacherId) throws ServiceException {
        List<Attendance> attendanceList = new ArrayList<>();
        Teacher teacher = getTeacher(teacherId);

        for (SaveAttendanceDTO dto : listDTO.getList()) {
            Attendance attendance = new Attendance();

            attendance.setDate(listDTO.getDate());
            attendance.setStudent(getStudent(dto.getId()));
            attendance.setTeacher(teacher);
            attendance.setIsPresent(dto.getPresent());

            attendanceList.add(attendance);
        }

        repository.saveAll(attendanceList);
    }
}

