package com.ideal.studentlog.controllers;

import com.ideal.studentlog.database.models.LeaveApplication;
import com.ideal.studentlog.helpers.dtos.LeaveApplicationDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/leave-applications")
@RequiredArgsConstructor
public class LeaveApplicationController {

    private final LeaveApplicationService service;

    @GetMapping
    public List<LeaveApplicationDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public LeaveApplicationDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveApplicationDTO create(@RequestBody @Valid LeaveApplicationDTO dto) throws ServiceException {
        return service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public LeaveApplicationDTO update(@PathVariable("id") Integer id, @RequestBody @Valid LeaveApplicationDTO dto) throws ServiceException {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
