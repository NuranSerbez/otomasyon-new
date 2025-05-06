package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.OgrenciProgramiRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.OgrenciProgramiResponseDTO;

import java.util.List;

public interface OgrenciProgramiService {
    List<OgrenciProgramiResponseDTO> findAll();

    OgrenciProgramiResponseDTO findById(Long id);

    OgrenciProgramiResponseDTO save(OgrenciProgramiRequestDTO OgrenciProgramiDTO);

    OgrenciProgramiResponseDTO update(Long id, OgrenciProgramiRequestDTO OgrenciProgramiDTO);

    void deleteById(Long id);
}
