package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.helpers.dataclass.StudentDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private static final StudentMapper mapper = StudentMapper.INSTANCE;

    private final StudentRepository repository;

    public List<StudentDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::studentToStudentDto)
                .collect(Collectors.toList());
    }

    public StudentDTO getById(Integer id) throws ServiceException {
        return mapper.studentToStudentDto(getStudent(id));
    }

    @Transactional
    public StudentDTO create(StudentDTO dto) {
        Student student = new Student();
        mapper.studentDtoToStudent(dto, student);

        return mapper.studentToStudentDto(repository.save(student));
    }

    @Transactional
    public StudentDTO update(Integer id, StudentDTO dto) throws ServiceException {
        Student student = getStudent(id);
        mapper.studentDtoToStudent(dto, student);

        return mapper.studentToStudentDto(repository.save(student));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Student getStudent(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Student not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }
}
