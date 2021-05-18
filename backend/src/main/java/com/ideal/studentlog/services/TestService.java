package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Test;
import com.ideal.studentlog.database.repositories.TestRepository;
import com.ideal.studentlog.helpers.dataclass.TestDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {
    private static final TestMapper mappper = TestMapper.INSTANCE;

    private final TestRepository repository;

    public List<TestDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mappper::testToTestDto)
                .collect(Collectors.toList());
    }

    public TestDTO getById(Integer id) throws ServiceException {
        return mappper.testToTestDto(getTest(id));
    }

    @Transactional
    public TestDTO create(TestDTO dto) {
        Test test = new Test();
        mappper.testDtoToTest(dto, test);

        return mappper.testToTestDto(repository.save(test));
    }

    @Transactional
    public TestDTO update(Integer id, TestDTO dto) throws ServiceException {
        Test test = getTest(id);
        mappper.testDtoToTest(dto, test);

        return mappper.testToTestDto(repository.save(test));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Test getTest(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Test not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }
}
