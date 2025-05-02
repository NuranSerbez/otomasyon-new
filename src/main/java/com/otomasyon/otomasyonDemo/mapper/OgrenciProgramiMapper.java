package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.requestDTO.OgrenciProgramiRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.OgrenciProgramiResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OgrenciProgramiMapper {

        @Mapping(target = "programId", expression = "java(String.valueOf(ogrenciProgrami.getProgram().getId()))")
        @Mapping(target = "userId", expression = "java(String.valueOf(ogrenciProgrami.getUser().getId()))")
        OgrenciProgramiResponseDTO toDTO(OgrenciProgrami ogrenciProgrami);

        @Mapping(target = "program", expression = "java(mapProgram(dto.getProgramId()))")
        @Mapping(target = "user", expression = "java(mapUser(dto.getUserId()))")
        OgrenciProgrami toEntity(OgrenciProgramiRequestDTO dto);

        default Program mapProgram(String programId) {
                if (programId == null) return null;
                Program program = new Program();
                program.setId(Long.parseLong(programId));
                return program;
        }

        default User mapUser(String userId) {
                if (userId == null) return null;
                User user = new User();
                user.setId(Long.parseLong(userId));
                return user;
        }
}
