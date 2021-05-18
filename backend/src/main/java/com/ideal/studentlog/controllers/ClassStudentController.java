package com.ideal.studentlog.controllers;


import com.ideal.studentlog.helpers.dtos.ClassStudentDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.ClassStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/class-students")
@RequiredArgsConstructor
public class ClassStudentController {

    private final ClassStudentService service;

    @GetMapping
    public List<ClassStudentDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public ClassStudentDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed("ADMIN")
    public ClassStudentDTO create(@RequestBody @Valid ClassStudentDTO dto) throws ServiceException {
        return service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    @RolesAllowed({"ADMIN", "TEACHER"})
    public ClassStudentDTO update(@PathVariable("id") Integer id, @RequestBody @Valid ClassStudentDTO dto) throws ServiceException {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
