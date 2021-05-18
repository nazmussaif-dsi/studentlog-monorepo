package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.ClassStudent;
import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.ClassDetails;
import com.ideal.studentlog.database.repositories.ClassDetailsRepository;
import com.ideal.studentlog.database.repositories.ClassStudentRepository;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.helpers.dtos.ClassStudentDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.ClassStudentMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassStudentService {
    private static final ClassStudentMapper mapper = ClassStudentMapper.INSTANCE;

    private final ClassStudentRepository repository;
    private final ClassDetailsRepository classDetailsRepository;
    private final StudentRepository studentRepository;

    public List<ClassStudentDTO> getAll(){
        return repository
                .findAll()
                .stream()
                .map(mapper::classStudentToClassStudentDto)
                .collect(Collectors.toList());
    }

    public ClassStudentDTO getById(Integer id) throws ServiceException {
        return mapper.classStudentToClassStudentDto(getClassStudent(id));
    }

    public ClassStudentDTO create(ClassStudentDTO dto) throws ServiceException {
        ClassStudent classStudent = new ClassStudent();

        mapper.classStudentDtoToClassStudent(dto, classStudent);
        classStudent.setStudent(getStudent(dto.getStudentId()));
        classStudent.setClassDetails(getClassDetails(dto.getClassDetailsId()));

        return mapper.classStudentToClassStudentDto(repository.save(classStudent));
    }

    public ClassStudentDTO update(Integer id, ClassStudentDTO dto) throws ServiceException {
        ClassStudent classStudent = getClassStudent(id);

        mapper.classStudentDtoToClassStudent(dto, classStudent);
        classStudent.setStudent(getStudent(dto.getStudentId()));
        classStudent.setClassDetails(getClassDetails(dto.getClassDetailsId()));

        return mapper.classStudentToClassStudentDto(repository.save(classStudent));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private ClassStudent getClassStudent(@NonNull Integer id) throws ServiceException {
        return repository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Class Student not found with ID: " + id,
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

    private ClassDetails getClassDetails(Integer id) throws ServiceException {
        return classDetailsRepository.findById(id).orElseThrow(() -> new ServiceException(
                "Class Details not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }
}
