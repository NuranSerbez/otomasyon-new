package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.responseDTO.DersAtamaResponseDTO;

import java.util.List;

public interface DersAtamaService {
    List<DersAtamaResponseDTO> findAll();

    DersAtamaResponseDTO findById(Long id);

    DersAtamaResponseDTO save(DersAtama dersAtamaDTO);

    DersAtamaResponseDTO update(Long id, DersAtama dersAtamaDTO);

    void deleteById(Long id);
}
