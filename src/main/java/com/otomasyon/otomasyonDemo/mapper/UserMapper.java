package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.requestDTO.UserRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.UserResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "rol", ignore = true) // Rol sonradan eklenecek
    User toEntity(UserRequestDTO dto);

    @Mapping(source = "rol.rolTuru", target = "rol")
    UserResponseDTO toDTO(User user);
}
