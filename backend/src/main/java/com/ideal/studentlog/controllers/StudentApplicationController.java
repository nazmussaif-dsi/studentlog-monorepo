package com.ideal.studentlog.controllers;


import com.ideal.studentlog.helpers.dataclass.StudentApplicationDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.StudentApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/student-applications")
@RequiredArgsConstructor
public class StudentApplicationController {

    private final StudentApplicationService service;

    @GetMapping
    public List<StudentApplicationDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public StudentApplicationDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentApplicationDTO create(@RequestBody @Valid StudentApplicationDTO dto) throws ServiceException {
        return service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public StudentApplicationDTO update(@PathVariable("id") Integer id, @RequestBody @Valid StudentApplicationDTO dto) throws ServiceException {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
