package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.requestDTO.RolRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.RolResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(source = "rolTuru", target = "rolTuru")
    Rol toEntity(RolRequestDTO dto);

    @Mapping(source = "rolTuru", target = "rolTuru")
    RolResponseDTO toDTO(Rol rol);

    default Rol.RolTuru stringToRolTuru(String rolTuruStr) {
        return rolTuruStr == null ? null : Rol.RolTuru.valueOf(rolTuruStr.toUpperCase());
    }

    default String rolTuruToString(Rol.RolTuru rolTuru) {
        return rolTuru == null ? null : rolTuru.name();
    }
}
