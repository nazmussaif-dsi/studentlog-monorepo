package com.ideal.studentlog.controllers;

import com.ideal.studentlog.helpers.dataclass.AdminDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public List<AdminDTO> getAll(){
        return adminService.getAll();
    }

    @GetMapping(path = "/{id}")
    public AdminDTO getById(@PathVariable("id") Integer id) throws ServiceException {
        return adminService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdminDTO create(@RequestBody @Valid AdminDTO dto){
        return adminService.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public AdminDTO update(@PathVariable("id") Integer id, @RequestBody @Valid AdminDTO dto) throws ServiceException{
        return adminService.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        adminService.delete(id);
    }
}
