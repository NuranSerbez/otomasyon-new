package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.requestDTO.OgrenciProgramiRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.OgrenciProgramiResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OgrenciProgramiMapper {

    @Mapping(source = "program.id", target = "programId")
    @Mapping(source = "user.id", target = "userId")
    OgrenciProgramiResponseDTO toDTO(OgrenciProgrami ogrenciProgrami);

    @Mapping(source = "program", target = "program")
    @Mapping(source = "user", target = "user")
    OgrenciProgrami toEntity(OgrenciProgramiRequestDTO dto);

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
