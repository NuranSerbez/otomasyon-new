package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.mapper.SoruMapper;
import com.otomasyon.otomasyonDemo.repository.SoruRepository;
import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SoruServiceImpl implements SoruService {

    private final SoruRepository soruRepository;
    private final SoruMapper soruMapper;

    @Autowired
    public SoruServiceImpl(SoruRepository soruRepository, SoruMapper soruMapper) {
        this.soruRepository = soruRepository;
        this.soruMapper = soruMapper;
    }

    @Override
    public List<SoruResponseDTO> findAll() {
        return soruRepository.findAll()
                .stream()
                .map(soruMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SoruResponseDTO> findById(Long id) {
        return soruRepository.findById(id)
                .map(soruMapper::toDTO);
    }

    @Override
    public SoruResponseDTO save(SoruRequestDTO soruDTO) {
        Soru soru = soruMapper.toEntity(soruDTO);
        Soru saved = soruRepository.save(soru);
        return soruMapper.toDTO(saved);
    }

    @Override
    public Optional<SoruResponseDTO> update(Long id, SoruRequestDTO soruDTO) {
        return soruRepository.findById(id).map(existing -> {
            existing.setSorular(soruDTO.getSorular());
            Soru updated = soruRepository.save(existing);
            return soruMapper.toDTO(updated);
        });
    }

    @Override
    public boolean deleteById(Long id) {
        if (soruRepository.existsById(id)) {
            soruRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
