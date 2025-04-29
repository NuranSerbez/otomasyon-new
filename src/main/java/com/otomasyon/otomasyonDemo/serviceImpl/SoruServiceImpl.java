package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.dto.SoruDTO;
import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.mapper.SoruMapper;
import com.otomasyon.otomasyonDemo.repository.SoruRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoruServiceImpl implements SoruService {

    private final SoruRepository soruRepository;

    @Autowired
    public SoruServiceImpl(SoruRepository soruRepository) {
        this.soruRepository = soruRepository;
    }

    @Override
    public List<SoruDTO> findAll() {
        return soruRepository.findAll()
                .stream()
                .map(SoruMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SoruDTO findById(Long id) {
        Soru soru = soruRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soru bulunamadı"));
        return SoruMapper.toDTO(soru);
    }

    @Override
    public SoruDTO save(SoruDTO soruDTO) {
        Soru soru = SoruMapper.toEntity(soruDTO);
        Soru savedSoru = soruRepository.save(soru);
        return SoruMapper.toDTO(savedSoru);
    }

    @Override
    public SoruDTO update(Long id, SoruDTO soruDTO) {
        Soru soru = soruRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Güncellenecek soru bulunamadı"));
        soru.setSorular(soruDTO.getSorular());
        Soru updatedSoru = soruRepository.save(soru);
        return SoruMapper.toDTO(updatedSoru);
    }

    @Override
    public void deleteById(Long id) {
        if (soruRepository.existsById(id)) {
            soruRepository.deleteById(id);
        }
    }
}
