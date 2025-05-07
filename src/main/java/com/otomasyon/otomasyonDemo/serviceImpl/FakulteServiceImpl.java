package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.FakulteMapper;
import com.otomasyon.otomasyonDemo.repository.FakulteRepository;
import com.otomasyon.otomasyonDemo.requestDTO.FakulteRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.FakulteResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.FakulteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakulteServiceImpl implements FakulteService {

    private final FakulteRepository fakulteRepository;
    private final FakulteMapper fakulteMapper;

    @Autowired
    public FakulteServiceImpl(FakulteRepository fakulteRepository, FakulteMapper fakulteMapper) {
        this.fakulteRepository = fakulteRepository;
        this.fakulteMapper = fakulteMapper;
    }

    @Override
    public List<FakulteResponseDTO> findAll() {
        return fakulteRepository.findAll()
                .stream()
                .map(fakulteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FakulteResponseDTO findById(Long id) {
        Fakulte fakulte = fakulteRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Fakülte bulunamadı: " + id));
        return fakulteMapper.toDTO(fakulte);
    }

    @Override
    public FakulteResponseDTO save(FakulteRequestDTO fakulteDTO) {
        Fakulte fakulte = fakulteMapper.toEntity(fakulteDTO);
        return fakulteMapper.toDTO(fakulteRepository.save(fakulte));
    }

    @Override
    public FakulteResponseDTO update(Long id, FakulteRequestDTO fakulteDTO) {
        Fakulte fakulte = fakulteRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Fakülte bulunamadı: " + id));
        Fakulte updateFakulte = fakulteRepository.save(fakulte);
        return fakulteMapper.toDTO(updateFakulte);
    }

    @Override
    public void deleteById(Long id) {
        Fakulte fakulte = fakulteRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Fakülte bulunamadı: " + id));
        fakulteRepository.delete(fakulte);
    }
}
