package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.dto.ProgramDTO;
import com.otomasyon.otomasyonDemo.entity.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {
    List<ProgramDTO> findAll();

    Optional<ProgramDTO> findById(Long id);

    ProgramDTO save(ProgramDTO programDTO);

    ProgramDTO update(Long id, ProgramDTO programDTO);

    void deleteById(Long id);
}
