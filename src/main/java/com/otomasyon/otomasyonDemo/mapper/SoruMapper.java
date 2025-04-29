package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.dto.SoruDTO;
import com.otomasyon.otomasyonDemo.entity.Soru;

public class SoruMapper {
    public static SoruDTO toDTO(Soru soru){
        SoruDTO dto = new SoruDTO();
        dto.setSorular(soru.getSorular());
    return dto;
    }
    public static Soru toEntity(SoruDTO dto){
        Soru soru = new Soru();
        soru.setSorular(dto.getSorular());
        return soru;
    }
}
