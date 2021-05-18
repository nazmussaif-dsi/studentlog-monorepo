package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Subject;
import com.ideal.studentlog.database.repositories.SubjectRepository;
import com.ideal.studentlog.helpers.dataclass.SubjectDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private static final SubjectMapper mapper = SubjectMapper.INSTANCE;

    private final SubjectRepository subjectRepository;

    public List<SubjectDTO> getAll(){
        return subjectRepository
                .findAll()
                .stream()
                .map(mapper::subjectToSubjectDto)
                .collect(Collectors.toList());
    }

    public SubjectDTO getById(Integer id) throws ServiceException {
        return mapper.subjectToSubjectDto(getSubject(id));
    }

    @Transactional
    public SubjectDTO create(SubjectDTO dto) {
        Subject subject = new Subject();
        mapper.subjectDtoToSubject(dto, subject);

        return mapper.subjectToSubjectDto(subjectRepository.save(subject));
    }

    @Transactional
    public SubjectDTO update(Integer id, SubjectDTO dto) throws ServiceException {
        Subject subject = getSubject(id);
        mapper.subjectDtoToSubject(dto, subject);

        return mapper.subjectToSubjectDto(subjectRepository.save(subject));
    }

    @Transactional
    public void delete(Integer id) {
        subjectRepository.deleteById(id);
    }

    private Subject getSubject(Integer id) throws ServiceException {
        return subjectRepository.findById(id).orElseThrow(() -> new ServiceException(
                "Subject not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }
}
