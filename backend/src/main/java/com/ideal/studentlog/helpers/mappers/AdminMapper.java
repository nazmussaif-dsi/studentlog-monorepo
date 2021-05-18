package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.Admin;
import com.ideal.studentlog.helpers.dataclass.AdminDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);


    AdminDTO adminToAdminDto(Admin admin);
    void adminDtoToAdmin(AdminDTO dto, @MappingTarget Admin admin);
}
