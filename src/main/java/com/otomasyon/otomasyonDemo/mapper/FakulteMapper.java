package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.requestDTO.FakulteRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.FakulteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FakulteMapper {

    @Mapping(source = "fakulteAdi", target = "fakulteAdi")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "bolumlerId", target = "bolumlerId")
    FakulteResponseDTO toDTO(Fakulte fakulte);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "fakulteAdi", target = "fakulteAdi")
    @Mapping(source = "bolumlerId", target = "bolumlerId")
    Fakulte toEntity(FakulteRequestDTO dto);
}

