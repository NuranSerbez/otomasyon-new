package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.OgrenciProgramiRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.OgrenciProgramiResponseDTO;

import java.util.List;
import java.util.Optional;

public interface OgrenciProgramiService {
    List<OgrenciProgramiResponseDTO> findAll();

    Optional <OgrenciProgramiResponseDTO> findById(Long id);

    OgrenciProgramiResponseDTO save(OgrenciProgramiRequestDTO OgrenciProgramiDTO);

    Optional <OgrenciProgramiResponseDTO> update(Long id, OgrenciProgramiRequestDTO OgrenciProgramiDTO);

    boolean deleteById(Long id);
}
