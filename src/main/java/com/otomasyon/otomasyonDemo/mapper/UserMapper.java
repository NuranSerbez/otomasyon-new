package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.requestDTO.UserRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.UserResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "rol.rolTuru", target = "rol")
    UserResponseDTO toDTO(User user);

    @Mapping(source = "rol", target = "rol.rolTuru")
    User toEntity(UserRequestDTO dto);

    default Rol mapStringToRol(String rolStr) {
        if (rolStr == null) return null;
        Rol rol = new Rol();
        rol.setRolTuru(Rol.RolTuru.valueOf(rolStr.toUpperCase()));
        return rol;
    }
}
