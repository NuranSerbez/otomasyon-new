package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProgramMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "programTuru", target = "programTuru")
    @Mapping(source = "bolumlerId", target = "bolumlerId")
    @Mapping(source = "ogrenciProgramlariId", target = "ogrenciProgramlariId")
    Program toEntity(ProgramRequestDTO dto);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "programTuru", target = "programTuru")
    @Mapping(source = "bolumlerId", target = "bolumlerId")
    @Mapping(source = "ogrenciProgramlariId", target = "ogrenciProgramlariId")
    ProgramResponseDTO toDTO(Program program);
}
