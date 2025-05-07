package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Degerlendirme;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.DegerlendirmeMapper;
import com.otomasyon.otomasyonDemo.repository.DegerlendirmeRepository;
import com.otomasyon.otomasyonDemo.requestDTO.DegerlendirmeRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DegerlendirmeResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DegerlendirmeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DegerlendirmeServiceImpl implements DegerlendirmeService {

    private final DegerlendirmeRepository degerlendirmeRepository;
    private final DegerlendirmeMapper degerlendirmeMapper;

    public DegerlendirmeServiceImpl(DegerlendirmeRepository degerlendirmeRepository, DegerlendirmeMapper degerlendirmeMapper) {
        this.degerlendirmeRepository = degerlendirmeRepository;
        this.degerlendirmeMapper = degerlendirmeMapper;
    }

    @Override
    public List<DegerlendirmeResponseDTO> findAll() {
        return degerlendirmeRepository.findAll()
                .stream()
                .map(degerlendirmeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DegerlendirmeResponseDTO findById(Long id) {
        Degerlendirme degerlendirme = degerlendirmeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Değerlendirme bulunamadı : " + id));
        return degerlendirmeMapper.toDTO(degerlendirme);
    }

    @Override
    public DegerlendirmeResponseDTO save(DegerlendirmeRequestDTO degerlendirmeDTO) {
        Degerlendirme degerlendirme = degerlendirmeMapper.toEntity(degerlendirmeDTO);
        return degerlendirmeMapper.toDTO(degerlendirmeRepository.save(degerlendirme));
    }

    @Override
    public DegerlendirmeResponseDTO update(Long id, DegerlendirmeRequestDTO degerlendirmeDTO) {
        Degerlendirme degerlendirme = degerlendirmeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Değerlendirme bulunamadı : " + id));
        User ogrenci = degerlendirmeMapper.mapUser(degerlendirmeDTO.getOgrenciId());
        degerlendirme.setOgrenci(ogrenci);
        User akademisyen = degerlendirmeMapper.mapUser(degerlendirmeDTO.getAkademisyenId());
        degerlendirme.setAkademisyen(akademisyen);
        Degerlendirme updatedDegerlendirme = degerlendirmeRepository.save(degerlendirme);
        return degerlendirmeMapper.toDTO(updatedDegerlendirme);
    }

    @Override
    public void deleteById(Long id) {
        Degerlendirme degerlendirme = degerlendirmeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Değerlendirme bulunamadı : " + id));
        degerlendirmeRepository.delete(degerlendirme);
    }
}
