package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.requestDTO.FakulteRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.FakulteResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FakulteMapper {
    Fakulte toEntity(FakulteRequestDTO dto);

    FakulteResponseDTO toDTO(Fakulte fakulte);
}