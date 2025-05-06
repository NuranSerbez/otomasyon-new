package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;

import java.util.List;

public interface SoruService {
    List<SoruResponseDTO> findAll();

    SoruResponseDTO findById(Long id);

    SoruResponseDTO save(SoruRequestDTO soruDTO);

    SoruResponseDTO update(Long id, SoruRequestDTO soruDTO);

    void deleteById(Long id);
}
