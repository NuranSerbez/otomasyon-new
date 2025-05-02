package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProgramService {
    List<ProgramResponseDTO> findAll();

    Optional<ProgramResponseDTO> findById(Long id);

    ProgramResponseDTO save(ProgramRequestDTO programDTO);

    Optional<ProgramResponseDTO> update(Long id, ProgramRequestDTO programDTO);

    boolean deleteById(Long id);
}