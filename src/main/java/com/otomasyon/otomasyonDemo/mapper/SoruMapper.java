package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SoruMapper {

    Soru toEntity(SoruRequestDTO dto);

    SoruResponseDTO toDTO(Soru soru);
}
