package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Admin;
import com.ideal.studentlog.database.repositories.AdminRepository;
import com.ideal.studentlog.helpers.dataclass.AdminDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private static final AdminMapper mapper = AdminMapper.INSTANCE;
    private final AdminRepository adminRepository;

    public List<AdminDTO> getAll(){
        return adminRepository
                .findAll()
                .stream()
                .map(mapper::adminToAdminDto)
                .collect(Collectors.toList());
    }

    public AdminDTO create(AdminDTO dto){
        Admin admin = new Admin();
        mapper.adminDtoToAdmin(dto, admin);

        return mapper.adminToAdminDto(adminRepository.save(admin));
    }

    public AdminDTO getById(Integer id) throws ServiceException{
        return mapper.adminToAdminDto(getAdmin(id));
    }

    public AdminDTO update(Integer id, AdminDTO dto) throws ServiceException{
        Admin admin = getAdmin(id);
        mapper.adminDtoToAdmin(dto, admin);

        return mapper.adminToAdminDto(adminRepository.save(admin));
    }

    public void delete(Integer id){
        adminRepository.deleteById(id);
    }

    private Admin getAdmin(Integer id) throws ServiceException {
        return adminRepository.findById(id).orElseThrow(() -> new ServiceException(
                "Admin not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }
}
