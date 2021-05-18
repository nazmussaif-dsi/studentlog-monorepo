package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.SchoolClass;
import com.ideal.studentlog.database.repositories.SchoolClassRepository;
import com.ideal.studentlog.helpers.dtos.SchoolClassDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.SchoolClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolClassService {
    private static final SchoolClassMapper mapper = SchoolClassMapper.INSTANCE;

    private final SchoolClassRepository repository;
    public List<SchoolClassDTO> getAll(){
        return repository
                .findAll()
                .stream()
                .map(mapper::schoolClassToSchoolClassDto)
                .collect(Collectors.toList());
    }

    public SchoolClassDTO getById(Integer id) throws ServiceException {
        return mapper.schoolClassToSchoolClassDto(getSchoolClass(id));
    }

    public SchoolClassDTO create(SchoolClassDTO dto){
        SchoolClass schoolClass = new SchoolClass();
        mapper.schoolClassDtoToSchoolClass(dto, schoolClass);

        return mapper.schoolClassToSchoolClassDto(repository.save(schoolClass));
    }

    public SchoolClassDTO update(Integer id, SchoolClassDTO dto) throws ServiceException {
        SchoolClass schoolClass = getSchoolClass(id);
        mapper.schoolClassDtoToSchoolClass(dto, schoolClass);

        return mapper.schoolClassToSchoolClassDto(repository.save(schoolClass));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private SchoolClass getSchoolClass(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "School Class not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }
}
