package com.otomasyon.otomasyonDemo.mapper;

import com.otomasyon.otomasyonDemo.dto.RolDTO;
import com.otomasyon.otomasyonDemo.entity.Rol;

public class RolMapper {
    public static RolDTO toDTO(Rol rol){
        RolDTO dto = new RolDTO();
        dto.setRolTuru(String.valueOf(rol.getRolTuru()));
        return dto;
    }
public static Rol toEntity(RolDTO dto){
        Rol rol = new Rol();
        rol.setRolTuru(Rol.RolTuru.valueOf(dto.getRolTuru()));
        return rol;
    }
}
