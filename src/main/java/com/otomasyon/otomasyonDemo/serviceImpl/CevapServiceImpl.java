package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Cevap;
import com.otomasyon.otomasyonDemo.entity.Degerlendirme;
import com.otomasyon.otomasyonDemo.entity.Soru;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.CevapMapper;
import com.otomasyon.otomasyonDemo.repository.CevapRepository;
import com.otomasyon.otomasyonDemo.repository.DegerlendirmeRepository;
import com.otomasyon.otomasyonDemo.repository.SoruRepository;
import com.otomasyon.otomasyonDemo.requestDTO.CevapRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.CevapResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.CevapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CevapServiceImpl implements CevapService {

    private final CevapRepository cevapRepository;
    private final CevapMapper cevapMapper;
    private final SoruRepository soruRepository;
    private final DegerlendirmeRepository degerlendirmeRepository;

    @Autowired
    public CevapServiceImpl(CevapRepository cevapRepository, CevapMapper cevapMapper, SoruRepository soruRepository, DegerlendirmeRepository degerlendirmeRepository) {
        this.cevapRepository = cevapRepository;
        this.cevapMapper = cevapMapper;
        this.soruRepository = soruRepository;
        this.degerlendirmeRepository = degerlendirmeRepository;
    }

    @Override
    public List<CevapResponseDTO> findAll() {
        return cevapRepository.findAll()
                .stream()
                .map(cevapMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CevapResponseDTO findById(Long id) {
       Cevap cevap = cevapRepository.findById(id)
               .orElseThrow(()-> new NotFoundException("Cevap bulunamadı: " + id));
       return cevapMapper.toDTO(cevap);
    }

    @Override
    public CevapResponseDTO save(CevapRequestDTO cevapDTO) {
        Cevap cevap = cevapMapper.toEntity(cevapDTO);
        return cevapMapper.toDTO(cevapRepository.save(cevap));
    }

    @Override
    public CevapResponseDTO update(Long id, CevapRequestDTO cevapDTO) {
        Cevap cevap = cevapRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Cevap bulunamadı: " + id));
        Soru soru = cevapMapper.mapSoru(Long.valueOf(cevapDTO.getSoru()));
        cevap.setSoru(soru);
        Degerlendirme degerlendirme = cevapMapper.mapDegerlendirme(Long.valueOf(cevapDTO.getDegerlendirme()));
        cevap.setDegerlendirme(degerlendirme);
        Cevap updatedCevap = cevapRepository.save(cevap);
        return cevapMapper.toDTO(updatedCevap);
    }

    @Override
    public void deleteById(Long id) {
        Cevap cevap = cevapRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Cevap bulunamadı: " + id));
        cevapRepository.delete(cevap);
    }
}
