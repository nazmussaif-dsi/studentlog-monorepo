package com.ideal.studentlog.controllers;

import com.ideal.studentlog.helpers.dataclass.AttendanceDTO;
import com.ideal.studentlog.helpers.dataclass.SaveAttendanceDTO;
import com.ideal.studentlog.helpers.dataclass.SaveAttendanceListDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping(path = "/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    
    private final AttendanceService service;

    @GetMapping
    public List<AttendanceDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public AttendanceDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttendanceDTO create(@RequestBody @Valid AttendanceDTO dto) throws ServiceException{
        return service.create(dto);
    }
    
    @PostMapping(path = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid SaveAttendanceListDTO listDTO) throws ServiceException{
        service.save(listDTO, 1);
    }

    @PatchMapping(path = "/{id}")
    public AttendanceDTO update(@PathVariable("id") Integer id,  @RequestBody @Valid AttendanceDTO dto) throws ServiceException {
        return service.update(id, dto);
    }
    
    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
          service.delete(id);
    }
}
