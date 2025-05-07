package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.requestDTO.DersAtamaRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DersAtamaResponseDTO;

import java.util.List;

public interface DersAtamaService {
    List<DersAtamaResponseDTO> findAll();

    DersAtamaResponseDTO findById(Long id);

    DersAtamaResponseDTO save(DersAtamaRequestDTO dersAtamaDTO);

    DersAtamaResponseDTO update(Long id, DersAtamaRequestDTO dersAtamaDTO);

    void deleteById(Long id);
}
