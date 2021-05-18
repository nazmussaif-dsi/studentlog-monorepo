package com.ideal.studentlog.controllers;


import com.ideal.studentlog.helpers.dtos.SchoolClassDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/school-classes")
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassService service;

    @GetMapping
    public List<SchoolClassDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public SchoolClassDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolClassDTO create(@RequestBody @Valid SchoolClassDTO dto) {
        return service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public SchoolClassDTO update(@PathVariable("id") Integer id, @RequestBody @Valid SchoolClassDTO dto) throws ServiceException {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
