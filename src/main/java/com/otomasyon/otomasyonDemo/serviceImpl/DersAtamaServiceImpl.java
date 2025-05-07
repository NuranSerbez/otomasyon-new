package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Ders;
import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.DersAtamaMapper;
import com.otomasyon.otomasyonDemo.repository.DersAtamaRepository;
import com.otomasyon.otomasyonDemo.repository.DersRepository;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.requestDTO.DersAtamaRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DersAtamaResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DersAtamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DersAtamaServiceImpl implements DersAtamaService {
    private final DersAtamaRepository dersAtamaRepository;
    private final DersAtamaMapper dersAtamaMapper;
    private final DersRepository dersRepository;
    private final UserRepository userRepository;

    @Autowired
    public DersAtamaServiceImpl(DersAtamaRepository dersAtamaRepository, DersAtamaMapper dersAtamaMapper, DersRepository dersRepository, UserRepository userRepository) {
        this.dersAtamaRepository = dersAtamaRepository;
        this.dersAtamaMapper = dersAtamaMapper;
        this.dersRepository = dersRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DersAtamaResponseDTO> findAll() {
        return dersAtamaRepository.findAll()
                .stream()
                .map(dersAtamaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DersAtamaResponseDTO findById(Long id) {
        DersAtama dersAtama = dersAtamaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ders atama bulunamadı: " + id));
        return dersAtamaMapper.toDTO(dersAtama);
    }

    @Override
    public DersAtamaResponseDTO save(DersAtamaRequestDTO dersAtamaDTO) {
        DersAtama dersAtama = dersAtamaMapper.toEntity(dersAtamaDTO);
        return dersAtamaMapper.toDTO(dersAtamaRepository.save(dersAtama));
    }

    @Override
    public DersAtamaResponseDTO update(Long id, DersAtamaRequestDTO dersAtamaDTO) {
        DersAtama dersAtama = dersAtamaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ders atama bulunamadı: " + id));
        Ders ders = dersAtamaMapper.mapDers(dersAtamaDTO.getDersId());
        dersAtama.setDers(ders);
        User user = dersAtamaMapper.mapUser(dersAtamaDTO.getUserId());
        dersAtama.setOgrenci(user);
        DersAtama updatedDersAtama = dersAtamaRepository.save(dersAtama);
        return dersAtamaMapper.toDTO(updatedDersAtama);
    }

    @Override
    public void deleteById(Long id) {
        DersAtama dersAtama = dersAtamaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ders atama bulunamadı: " + id));
        dersAtamaRepository.delete(dersAtama);
    }
}
