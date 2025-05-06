package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;

import java.util.List;

public interface ProgramService {
    List<ProgramResponseDTO> findAll();

    ProgramResponseDTO findById(Long id);

    ProgramResponseDTO save(ProgramRequestDTO programDTO);

    ProgramResponseDTO update(Long id, ProgramRequestDTO programDTO);

    void deleteById(Long id);
}