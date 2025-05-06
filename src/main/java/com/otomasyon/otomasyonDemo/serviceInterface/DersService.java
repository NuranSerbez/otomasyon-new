package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.DersRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DersResponseDTO;

import java.util.List;

public interface DersService {
    List<DersResponseDTO> findAll();

    DersResponseDTO findById(Long id);

    DersResponseDTO save(DersRequestDTO dersDTO);

    DersResponseDTO update(Long id, DersRequestDTO dersDTO);

    void deleteById(Long id);
}
