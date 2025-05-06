package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.NotRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.NotResponseDTO;

import java.util.List;

public interface NotService {

    List<NotResponseDTO> findAll();

    NotResponseDTO findById(Long id);

    NotResponseDTO save(NotRequestDTO notDTO);

    NotResponseDTO update(Long id, NotRequestDTO notDTO);

    void deleteById(Long id);
}