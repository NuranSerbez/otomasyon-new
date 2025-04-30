package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.dto.RolRequestDTO;
import com.otomasyon.otomasyonDemo.dto.RolResponseDTO;
import com.otomasyon.otomasyonDemo.entity.Rol;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rolTuru", expression = "java(Rol.RolTuru.valueOf(dto.getRolTuru().toUpperCase()))")
    Rol toEntity(RolRequestDTO dto);

    @Mapping(source = "rolTuru", target = "rolTuru")
    RolResponseDTO toDTO(Rol rol);
}
