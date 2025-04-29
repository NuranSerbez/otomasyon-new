package com.otomasyon.otomasyonDemo.serviceInterface;

import com.otomasyon.otomasyonDemo.dto.SoruDTO;

import java.util.List;

public interface SoruService {
    List<SoruDTO> findAll();

    SoruDTO findById(Long id);

    SoruDTO save(SoruDTO soruDTO);

    SoruDTO update(Long id, SoruDTO soruDTO);

    void deleteById(Long id);
}
