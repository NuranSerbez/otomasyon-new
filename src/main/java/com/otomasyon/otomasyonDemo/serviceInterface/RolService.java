package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.requestDTO.RolRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.RolResponseDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<RolResponseDTO> findAll();
    Optional<RolResponseDTO> findById(Long id);
    RolResponseDTO save(RolRequestDTO rolDTO);
    RolResponseDTO update(Long id, RolRequestDTO rolDTO);
    void deleteById(Long id);
    Optional<Rol> findByRolTuru(Rol.RolTuru rolTuru);
}
