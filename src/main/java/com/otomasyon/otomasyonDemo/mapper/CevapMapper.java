package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Cevap;
import com.otomasyon.otomasyonDemo.entity.Degerlendirme;
import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.requestDTO.CevapRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.CevapResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CevapMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "puan", target = "puan")
    @Mapping(source = "degerlendirme.id", target = "degerlendirmeId")
    @Mapping(source = "soru.id", target = "soruId")
    CevapResponseDTO toDTO(Cevap cevap);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "puan", target = "puan")
    @Mapping(source = "degerlendirme", target = "degerlendirme")
    @Mapping(source = "soru", target = "soru")
    Cevap toEntity(CevapRequestDTO dto);

    default Degerlendirme mapDegerlendirme(Long degerlendirmeId) {
        if (degerlendirmeId == null) return null;
        Degerlendirme degerlendirme = new Degerlendirme();
        degerlendirme.setId(degerlendirmeId);
        return degerlendirme;
    }

    default Soru mapSoru(Long soruId) {
        if (soruId == null) return null;
        Soru soru = new Soru();
        soru.setId(soruId);
        return soru;
    }
}
