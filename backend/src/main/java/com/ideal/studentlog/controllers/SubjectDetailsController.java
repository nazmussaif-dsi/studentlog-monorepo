package com.ideal.studentlog.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ideal.studentlog.helpers.dataclass.SubjectDetailsDTO;
import com.ideal.studentlog.services.SubjectDetailsService;
import com.ideal.studentlog.helpers.exceptions.ServiceException;

@RestController
@RequestMapping(path = "/subject-details")
@RequiredArgsConstructor
public class SubjectDetailsController {
    private final SubjectDetailsService service;

    @GetMapping
    public List<SubjectDetailsDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public SubjectDetailsDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectDetailsDTO create(@RequestBody SubjectDetailsDTO dto) throws ServiceException {
        return service.create(dto);
    }
    
    @PatchMapping(path = "/{id}")
    public SubjectDetailsDTO update(@PathVariable("id") Integer id,  @RequestBody SubjectDetailsDTO dto) throws ServiceException {
        return service.update(id, dto);
    }
    
    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
          service.delete(id);
    }
}
