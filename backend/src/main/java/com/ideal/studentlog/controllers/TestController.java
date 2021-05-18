package com.ideal.studentlog.controllers;

import com.ideal.studentlog.helpers.dataclass.TestDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @GetMapping
    public List<TestDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public TestDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestDTO create(@RequestBody @Valid TestDTO dto) {
        return service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public TestDTO update(@PathVariable("id") Integer id, @RequestBody @Valid TestDTO dto) throws ServiceException {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}
