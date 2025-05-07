package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.DevamsizlikRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DevamsizlikResponseDTO;

import java.util.List;

public interface DevamsizlikService {
    List<DevamsizlikResponseDTO> findAll();

    DevamsizlikResponseDTO findById(Long id);

    DevamsizlikResponseDTO save(DevamsizlikRequestDTO devamsizlikDTO);

    DevamsizlikResponseDTO update(Long id, DevamsizlikRequestDTO devamsizlikDTO);

    void deleteById(Long id);
}
