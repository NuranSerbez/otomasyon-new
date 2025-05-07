package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SoruMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "sorular", target = "sorular")
    SoruResponseDTO toDTO(Soru soru);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "sorular", target = "sorular")
    Soru toEntity(SoruRequestDTO dto);
}
