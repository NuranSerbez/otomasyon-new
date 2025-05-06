package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    @Mapping(source = "programTuru", target = "programTuru")
    Program toEntity(ProgramRequestDTO dto);

    @Mapping(source = "programTuru", target = "programTuru")
    ProgramResponseDTO toDTO(Program program);
}
