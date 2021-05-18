package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.Test;
import com.ideal.studentlog.database.models.TestResult;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.database.repositories.TestRepository;
import com.ideal.studentlog.database.repositories.TestResultRepository;
import com.ideal.studentlog.helpers.dataclass.TestResultDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.TestResultMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestResultService {
    private static final TestResultMapper mapper = TestResultMapper.INSTANCE;

    private final TestResultRepository repository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;

    public List<TestResultDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::testResultToTestResultDto)
                .collect(Collectors.toList());
    }

    public List<TestResultDTO> getByStudentId(Integer id) throws ServiceException {
        return repository
                .findByStudentId(id)
                .stream()
                .map(mapper::testResultToTestResultDto)
                .collect(Collectors.toList());
    }

    public TestResultDTO getById(Integer id) throws ServiceException {
        return mapper.testResultToTestResultDto(getTestResult(id));
    }

    @Transactional
    public TestResultDTO create(TestResultDTO dto) throws ServiceException {
        TestResult test = new TestResult();

        mapper.testResultDtoToTestResult(dto, test);
        test.setStudent(getStudent(dto.getStudentId()));
        test.setTest(getTest(dto.getTestId()));

        return mapper.testResultToTestResultDto(repository.save(test));
    }

    @Transactional
    public TestResultDTO update(Integer id, TestResultDTO dto) throws ServiceException {
        TestResult test = getTestResult(id);

        mapper.testResultDtoToTestResult(dto, test);
        test.setStudent(getStudent(dto.getStudentId()));
        test.setTest(getTest(dto.getTestId()));

        return mapper.testResultToTestResultDto(repository.save(test));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private TestResult getTestResult(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Test Result not found with ID: " + id,
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

    private Test getTest(@NonNull Integer id) throws ServiceException {
        return testRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Test not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }
}
