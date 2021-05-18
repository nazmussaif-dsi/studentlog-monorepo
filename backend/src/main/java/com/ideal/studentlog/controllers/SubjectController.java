package com.ideal.studentlog.controllers;

import com.ideal.studentlog.helpers.dataclass.SubjectDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectDTO> getAll(){
        return subjectService.getAll();
    }

    @GetMapping(path = "/{id}")
    public SubjectDTO getById(@PathVariable("id") Integer id) throws ServiceException{
        return subjectService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectDTO create(@RequestBody @Valid SubjectDTO dto){
        return subjectService.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public SubjectDTO update (@PathVariable("id") Integer id, @RequestBody @Valid SubjectDTO dto) throws ServiceException {
        return subjectService.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        subjectService.delete(id);
    }
}
