package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.BolumRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.BolumResponseDTO;

import java.util.List;

public interface BolumService {
    List<BolumResponseDTO> findAll();

    BolumResponseDTO findById(Long id);

    BolumResponseDTO save(BolumRequestDTO bolumDTO);

    BolumResponseDTO update(Long id, BolumRequestDTO bolumDTO);

    void deleteById(Long id);
}
