package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.dto.RolDTO;
import com.otomasyon.otomasyonDemo.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<RolDTO> findAll();

    Optional<RolDTO> findById(Long id);

    RolDTO save(RolDTO rolDTO);

    RolDTO update(Long id, RolDTO rolDTO);

    void deleteById(Long id);

    Optional<Object> findByRolTuru(Rol.RolTuru rolTuru);
}