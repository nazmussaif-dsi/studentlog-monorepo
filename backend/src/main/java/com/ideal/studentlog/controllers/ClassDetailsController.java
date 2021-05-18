package com.ideal.studentlog.controllers;


import com.ideal.studentlog.database.models.ClassDetails;
import com.ideal.studentlog.helpers.dtos.ClassDetailsDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.ClassDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/class-details")
@RequiredArgsConstructor
public class ClassDetailsController {

    private final ClassDetailsService service;

    @GetMapping
    public List<ClassDetailsDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public ClassDetailsDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassDetailsDTO create(@RequestBody @Valid ClassDetailsDTO dto) throws ServiceException {
        return service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public ClassDetailsDTO update(@PathVariable("id") Integer id, @RequestBody @Valid ClassDetailsDTO dto) throws ServiceException {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
