package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.SoruMapper;
import com.otomasyon.otomasyonDemo.repository.SoruRepository;
import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public SoruResponseDTO findById(Long id) {
        Soru soru = soruRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Soru bulunamadı: " + id));
        return soruMapper.toDTO(soru);
    }

    @Override
    public SoruResponseDTO save(SoruRequestDTO dto) {
        Soru soru = soruMapper.toEntity(dto);
        return soruMapper.toDTO(soruRepository.save(soru));
    }

    @Override
    public SoruResponseDTO update(Long id, SoruRequestDTO dto) {
        Soru soru = soruRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Soru bulunamadı: " + id));
       soru.setSorular(dto.getSorular());
       return soruMapper.toDTO(soruRepository.save(soru));
    }

    @Override
    public void deleteById(Long id) {
        Soru soru = soruRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Soru bulunamadı"));
        soruRepository.delete(soru);
    }
}
