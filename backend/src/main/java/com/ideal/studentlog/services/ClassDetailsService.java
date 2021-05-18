package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.ClassDetails;
import com.ideal.studentlog.database.models.SchoolClass;
import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.database.repositories.ClassDetailsRepository;
import com.ideal.studentlog.database.repositories.SchoolClassRepository;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.dtos.ClassDetailsDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.ClassDetailsMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassDetailsService {
    private static final ClassDetailsMapper mapper = ClassDetailsMapper.INSTANCE;

    private final ClassDetailsRepository repository;
    private final SchoolClassRepository schoolClassRepository;
    private final TeacherRepository teacherRepository;

    public List<ClassDetailsDTO> getAll(){
        return repository
                .findAll()
                .stream()
                .map(mapper::classDetailsToClassDetailsDto)
                .collect(Collectors.toList());
    }

    public ClassDetailsDTO getById(Integer id) throws ServiceException {
        return mapper.classDetailsToClassDetailsDto(getClassDetails(id));
    }

    public ClassDetailsDTO create(ClassDetailsDTO dto) throws ServiceException {
        ClassDetails classDetails = new ClassDetails();

        mapper.classDetailsDtoToClassDetails(dto, classDetails);
        classDetails.setSchoolClass(getSchoolClass(dto.getSchoolClassId()));
        classDetails.setTeacher(getTeacher(dto.getTeacherId()));

        return mapper.classDetailsToClassDetailsDto(repository.save(classDetails));
    }

    public ClassDetailsDTO update(Integer id, ClassDetailsDTO dto) throws ServiceException {
        ClassDetails classDetails = getClassDetails(id);

        mapper.classDetailsDtoToClassDetails(dto, classDetails);
        classDetails.setSchoolClass(getSchoolClass(dto.getSchoolClassId()));
        classDetails.setTeacher(getTeacher(dto.getTeacherId()));

        return mapper.classDetailsToClassDetailsDto(repository.save(classDetails));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private ClassDetails getClassDetails(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Class Details not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private SchoolClass getSchoolClass(@NonNull Integer id) throws ServiceException {
        return schoolClassRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "School Class not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    private Teacher getTeacher(@NonNull Integer id) throws ServiceException {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Teacher not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }
}
