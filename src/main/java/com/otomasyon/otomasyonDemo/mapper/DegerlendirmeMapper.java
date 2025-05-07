package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Degerlendirme;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.requestDTO.DegerlendirmeRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DegerlendirmeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DegerlendirmeMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "ogrenci.id", target = "ogrenciId")
    @Mapping(source = "akademisyen.id", target = "akademisyenId")
    DegerlendirmeResponseDTO toDTO(Degerlendirme degerlendirme);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "ogrenciId", target = "ogrenci", qualifiedByName = "mapUserById")
    @Mapping(source = "akademisyenId", target = "akademisyen", qualifiedByName = "mapUserById")
    Degerlendirme toEntity(DegerlendirmeRequestDTO dto);

    @Named("mapUserById")
    default User mapUser(Long userId) {
        if (userId == null) return null;
        User user = new User();
        user.setId(userId);
        return user;
    }
}