package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.requestDTO.CevapRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.CevapResponseDTO;

import java.util.List;

public interface CevapService {
    List<CevapResponseDTO> findAll();

    CevapResponseDTO findById(Long id);

    CevapResponseDTO save(CevapRequestDTO cevapDTO);

    CevapResponseDTO update(Long id, CevapRequestDTO cevapDTO);

    void deleteById(Long id);
}
