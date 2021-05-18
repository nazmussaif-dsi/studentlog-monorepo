package com.ideal.studentlog.controllers;

import com.ideal.studentlog.helpers.dataclass.TestResultDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/test-results")
@RequiredArgsConstructor
public class TestResultController {

    private final TestResultService service;

    @GetMapping
    public List<TestResultDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/student/{id}")
    public List<TestResultDTO> getByStudentId(@PathVariable("id") Integer id) throws ServiceException {
        return service.getByStudentId(id);
    }

    @GetMapping(path = "/{id}")
    public TestResultDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestResultDTO create(@RequestBody @Valid TestResultDTO dto) throws ServiceException {
        return service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public TestResultDTO update(@PathVariable("id") Integer id, @RequestBody @Valid TestResultDTO dto) throws ServiceException {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}
