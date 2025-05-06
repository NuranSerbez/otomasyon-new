package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.DegerlendirmeRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DegerlendirmeResponseDTO;

import java.util.List;

public interface DegerlendirmeService {
    List<DegerlendirmeResponseDTO> findAll();

    DegerlendirmeResponseDTO findById(Long id);

    DegerlendirmeResponseDTO save(DegerlendirmeRequestDTO degerlendirmeDTO);

    DegerlendirmeResponseDTO update(Long id, DegerlendirmeRequestDTO degerlendirmeDTO);

    void deleteById(Long id);
}
