package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.NotRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.NotResponseDTO;

import java.util.List;
import java.util.Optional;

public interface NotService {

    List<NotResponseDTO> findAll();

    Optional<NotResponseDTO> findById(Long id);

    NotResponseDTO save(NotRequestDTO notDTO);

    Optional<NotResponseDTO> update(Long id, NotRequestDTO notDTO);

    boolean deleteById(Long id);
}