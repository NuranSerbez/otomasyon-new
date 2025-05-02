package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;

import java.util.List;
import java.util.Optional;

public interface SoruService {
    List<SoruResponseDTO> findAll();

    Optional<SoruResponseDTO> findById(Long id);

    SoruResponseDTO save(SoruRequestDTO soruDTO);

    Optional<SoruResponseDTO> update(Long id, SoruRequestDTO soruDTO);

    boolean deleteById(Long id);
}
