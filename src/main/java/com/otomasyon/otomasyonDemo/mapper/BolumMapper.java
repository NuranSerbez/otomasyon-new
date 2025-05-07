package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.requestDTO.BolumRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.BolumResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BolumMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "bolumAdi", target = "bolumAdi")
    @Mapping(source = "program.id", target = "programId")
    @Mapping(source = "fakulte.id", target = "fakulteId")
    BolumResponseDTO toDTO(Bolum bolum);
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "bolumAdi", target = "bolumAdi")
    @Mapping(source = "program", target = "program")
    @Mapping(source = "fakulte", target = "fakulte")
    Bolum toEntity(BolumRequestDTO dto);

    default Program mapProgram(Long programId) {
        if (programId == null) return null;
        Program program = new Program();
        program.setId(programId);
        return program;
    }

    default Fakulte mapFakulte(Long fakulteId) {
        if (fakulteId == null) return null;
        Fakulte fakulte = new Fakulte();
        fakulte.setId(fakulteId);
        return fakulte;
    }
}
