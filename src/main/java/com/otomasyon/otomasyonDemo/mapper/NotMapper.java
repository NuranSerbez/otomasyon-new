package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.entity.Not;
import com.otomasyon.otomasyonDemo.requestDTO.NotRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.NotResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotMapper {

    @Mapping(source = "dersAtama.id", target = "dersAtamaId")
    @Mapping(source = "vize", target = "vize")
    @Mapping(source = "finl", target = "finl")
    @Mapping(source = "ortalama", target = "ortalama")
    NotResponseDTO toDTO(Not not);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dersAtamaId", target = "dersAtama")
    @Mapping(source = "vize", target = "vize")
    @Mapping(source = "finl", target = "finl")
    @Mapping(source = "ortalama", target = "ortalama")
    Not toEntity(NotRequestDTO dto);

    default DersAtama mapDersAtama(Long dersAtamaId) {
        if (dersAtamaId == null) return null;
        DersAtama dersAtama = new DersAtama();
        dersAtama.setId(dersAtamaId);
        return dersAtama;
    }
}

