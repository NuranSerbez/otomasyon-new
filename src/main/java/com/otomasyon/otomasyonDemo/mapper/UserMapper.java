package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.requestDTO.UserRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.UserResponseDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {OgrenciProgramiMapper.class})
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "isim", target = "isim")
    @Mapping(source = "soyisim", target = "soyisim")
    @Mapping(source = "tckn", target = "tckn")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "adres", target = "adres")
    @Mapping(source = "telefon", target = "telefon")
    @Mapping(source = "sifreGuncelligi", target = "sifreGuncelligi")
    @Mapping(source = "rol.rolTuru", target = "rol")
    @Mapping(source = "ogrenciProgramlari", target = "ogrenciProgramlari")
    UserResponseDTO toDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "isim", target = "isim")
    @Mapping(source = "soyisim", target = "soyisim")
    @Mapping(source = "tckn", target = "tckn")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "adres", target = "adres")
    @Mapping(source = "telefon", target = "telefon")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "sifreGuncelligi", target = "sifreGuncelligi")
    @Mapping(source = "rol", target = "rol", qualifiedByName = "mapStringToRol")
    @Mapping(target = "ogrenciProgramlari", ignore = true)
    User toEntity(UserRequestDTO dto);

    @Named("mapStringToRol")
    default Rol mapStringToRol(String rolStr) {
        if (rolStr == null) return null;
        Rol rol = new Rol();
        rol.setRolTuru(Rol.RolTuru.valueOf(rolStr.toUpperCase()));
        return rol;
    }
}

