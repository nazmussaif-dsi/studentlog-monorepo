package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.LeaveApplication;
import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.database.repositories.LeaveApplicationRepository;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.dtos.LeaveApplicationDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.LeaveApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveApplicationService {
    private static final LeaveApplicationMapper mapper = LeaveApplicationMapper.INSTANCE;

    private final LeaveApplicationRepository repository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public List<LeaveApplicationDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::leaveApplicationToLeaveApplicationDto)
                .collect(Collectors.toList());
    }

    public LeaveApplicationDTO getById(Integer id) throws ServiceException {
        return mapper.leaveApplicationToLeaveApplicationDto(getLeaveApplication(id));
    }

    @Transactional
    public LeaveApplicationDTO create(LeaveApplicationDTO dto) throws ServiceException {
        LeaveApplication leaveApplication = new LeaveApplication();

        mapper.leaveApplicationDtoToLeaveApplication(dto, leaveApplication);
        leaveApplication.setApprovedBy(getTeacher(dto.getApprovedById()));
        leaveApplication.setStudent(getStudent(dto.getStudentId()));

        return mapper.leaveApplicationToLeaveApplicationDto(repository.save(leaveApplication));
    }

    @Transactional
    public LeaveApplicationDTO update(Integer id, LeaveApplicationDTO dto) throws ServiceException {
        LeaveApplication leaveApplication = getLeaveApplication(id);

        mapper.leaveApplicationDtoToLeaveApplication(dto, leaveApplication);
        leaveApplication.setApprovedBy(getTeacher(dto.getApprovedById()));
        leaveApplication.setStudent(getStudent(dto.getStudentId()));

        return mapper.leaveApplicationToLeaveApplicationDto(repository.save(leaveApplication));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public LeaveApplication getLeaveApplication(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Leave Application not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private Teacher getTeacher(Integer id) throws ServiceException {
        return teacherRepository.findById(id).orElseThrow(() -> new ServiceException(
                "Teacher not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private Student getStudent(Integer id) throws ServiceException {
        return studentRepository.findById(id).orElseThrow(() -> new ServiceException(
                "Student not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }
}
