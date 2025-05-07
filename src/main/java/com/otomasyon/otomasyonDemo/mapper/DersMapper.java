package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Ders;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.requestDTO.DersRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DersResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DersMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "program.id", target = "programId")
    @Mapping(source = "akademisyen.id", target = "userId")
    DersResponseDTO toDTO(Ders ders);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "programId", target = "program")
    @Mapping(source = "userId", target = "akademisyen")
    Ders toEntity(DersRequestDTO dto);

    default Program mapProgram(Long programId) {
        if (programId == null) return null;
        Program program = new Program();
        program.setId(programId);
        return program;
    }

    default User mapUser(Long userId) {
        if (userId == null) return null;
        User user = new User();
        user.setId(userId);
        return user;
    }
}
