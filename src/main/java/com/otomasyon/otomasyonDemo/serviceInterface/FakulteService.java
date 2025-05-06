package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.FakulteRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.FakulteResponseDTO;

import java.util.List;

public interface FakulteService {
    List<FakulteResponseDTO> findAll();
    FakulteResponseDTO findById(Long id);
    FakulteResponseDTO save(FakulteRequestDTO fakulteDTO);
    FakulteResponseDTO update(Long id, FakulteRequestDTO fakulteDTO);
    void deleteById(Long id);
}

