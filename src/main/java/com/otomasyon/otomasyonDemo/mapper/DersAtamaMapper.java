package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Ders;
import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.requestDTO.DersAtamaRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DersAtamaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

    @Mapper(componentModel = "spring")
    public interface DersAtamaMapper {
        @Mapping(source = "id", target = "id")
        @Mapping(source = "ders.id", target = "dersId")
        @Mapping(source = "ogrenci.id", target = "userId")
        @Mapping(source = "onaydurum", target = "onaydurum")
        DersAtamaResponseDTO toDTO(DersAtama dersAtama);
        @Mapping(target = "id", ignore = true)
        @Mapping(source = "dersId", target = "ders")
        @Mapping(source = "userId", target = "ogrenci")
        @Mapping(source = "onaydurum", target = "onaydurum")
        DersAtama toEntity(DersAtamaRequestDTO dto);

        default Ders mapDers(Long dersId) {
            if (dersId == null) return null;
            Ders ders = new Ders();
            ders.setId(dersId);
            return ders;
        }

        default User mapUser(Long userId) {
            if (userId == null) return null;
            User user = new User();
            user.setId(userId);
            return user;
        }
    }
