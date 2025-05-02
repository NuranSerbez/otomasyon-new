package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bolumler", ignore = true)
    @Mapping(target = "ogrenciProgramlari", ignore = true)
    Program toEntity(ProgramRequestDTO dto);

    ProgramResponseDTO toDTO(Program program);
}