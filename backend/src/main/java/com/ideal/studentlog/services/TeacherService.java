package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.dataclass.TeacherDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private static final TeacherMapper mapper = TeacherMapper.INSTANCE;

    private final TeacherRepository repository;

    public List<TeacherDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::teacherToTeacherDto)
                .collect(Collectors.toList());
    }

    public TeacherDTO getById(Integer id) throws ServiceException {
        return mapper.teacherToTeacherDto(getTeacher(id));
    }

    @Transactional
    public TeacherDTO create(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        mapper.teacherDtoToTeacher(dto, teacher);

        return mapper.teacherToTeacherDto(repository.save(teacher));
    }

    @Transactional
    public TeacherDTO update(Integer id, TeacherDTO dto) throws ServiceException {
        Teacher teacher = getTeacher(id);
        mapper.teacherDtoToTeacher(dto, teacher);

        return mapper.teacherToTeacherDto(teacher);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Teacher getTeacher(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Teacher not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }
}
