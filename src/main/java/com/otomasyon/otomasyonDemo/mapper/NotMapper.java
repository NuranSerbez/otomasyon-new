package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.entity.Not;
import com.otomasyon.otomasyonDemo.requestDTO.NotRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.NotResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NotMapper {

    @Mapping(target = "dersAtamaId", source = "dersAtama.id")
    NotResponseDTO toDTO(Not not);

    @Mapping(target = "dersAtama", expression = "java(mapToDersAtama(dto.getDersAtamaId()))")
    Not toEntity(NotRequestDTO dto);

    default DersAtama mapToDersAtama(Long dersAtamaId) {
        if (dersAtamaId == null) return null;
        DersAtama dersAtama = new DersAtama();
        dersAtama.setId(dersAtamaId);
        return dersAtama;
    }
}
