package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.requestDTO.RolRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.RolResponseDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "rolTuru", target = "rolTuru")
    Rol toEntity(RolRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "rolTuru", target = "rolTuru")
    RolResponseDTO toDTO(Rol rol);
}

