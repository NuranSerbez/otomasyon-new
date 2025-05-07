package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.entity.Devamsizlik;
import com.otomasyon.otomasyonDemo.requestDTO.DevamsizlikRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DevamsizlikResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DevamsizlikMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "toplamSaat", target = "toplamSaat")
    @Mapping(source = "dersAtama.id", target = "dersAtamaId")
    DevamsizlikResponseDTO toDTO(Devamsizlik devamsizlik);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "toplamSaat", target = "toplamSaat")
    @Mapping(source = "dersAtamaId", target = "dersAtama", qualifiedByName = "mapToDersAtama")
    Devamsizlik toEntity(DevamsizlikRequestDTO dto);

    @Named("mapToDersAtama")
    default DersAtama mapToDersAtama(Long id) {
        if (id == null) return null;
        DersAtama dersAtama = new DersAtama();
        dersAtama.setId(id);
        return dersAtama;
    }
}