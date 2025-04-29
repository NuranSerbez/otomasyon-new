package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.dto.ProgramDTO;
import com.otomasyon.otomasyonDemo.entity.Program;

public class ProgramMapper {
    public static ProgramDTO toDTO(Program program){
        ProgramDTO dto = new ProgramDTO();
        dto.setProgramTuru(program.getProgramTuru());
        return dto;
    }
    public static Program toEntity(ProgramDTO dto){
        Program program = new Program();
        program.setProgramTuru(dto.getProgramTuru());
        return program;
    }
}
