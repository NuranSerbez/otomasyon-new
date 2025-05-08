package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.BolumMapper;
import com.otomasyon.otomasyonDemo.repository.BolumRepository;
import com.otomasyon.otomasyonDemo.repository.FakulteRepository;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import com.otomasyon.otomasyonDemo.requestDTO.BolumRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.BolumResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.BolumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BolumServiceImpl implements BolumService {

    private final BolumRepository bolumRepository;
    private final BolumMapper bolumMapper;
    private final FakulteRepository fakulteRepository;
    private final ProgramRepository programRepository;

    @Autowired
    public BolumServiceImpl(BolumRepository bolumRepository, BolumMapper bolumMapper, FakulteRepository fakulteRepository, ProgramRepository programRepository) {
        this.bolumRepository = bolumRepository;
        this.bolumMapper = bolumMapper;
        this.fakulteRepository = fakulteRepository;
        this.programRepository = programRepository;
    }

    @Override
    public List<BolumResponseDTO> findAll() {
        return bolumRepository.findAll()
                .stream()
                .map(bolumMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BolumResponseDTO findById(Long id) {
        Bolum bolum = bolumRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bölüm bulunamadı: " + id));
        return bolumMapper.toDTO(bolum);
    }

    @Override
    public BolumResponseDTO save(BolumRequestDTO bolumDTO) {
        Fakulte fakulte = fakulteRepository.findById(bolumDTO.getFakulte().getId())
                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı"));

        Program program = programRepository.findById(bolumDTO.getProgram().getId())
                .orElseThrow(() -> new RuntimeException("Program bulunamadı"));

        Bolum bolum = new Bolum();
        bolum.setBolumAdi(bolumDTO.getBolumAdi());
        bolum.setFakulte(fakulte);
        bolum.setProgram(program);

        Bolum savedBolum = bolumRepository.save(bolum);

        return bolumMapper.toDTO(savedBolum);
    }
    @Override
    public BolumResponseDTO update(Long id, BolumRequestDTO bolumDTO) {
        Bolum bolum = bolumRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bölüm bulunamadı: " + id));
        Program program = bolumMapper.mapProgram(bolumDTO.getProgram().getId());
        bolum.setProgram(program);
        Fakulte fakulte = bolumMapper.mapFakulte(bolumDTO.getFakulte().getId());
        bolum.setFakulte(fakulte);
        Bolum updatedBolum = bolumRepository.save(bolum);
        return bolumMapper.toDTO(updatedBolum);
    }

    @Override
    public void deleteById(Long id) {
        Bolum bolum = bolumRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bölüm bulunamadı: " + id));
        bolumRepository.delete(bolum);
    }
}