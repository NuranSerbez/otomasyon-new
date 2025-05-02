package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Not;
import com.otomasyon.otomasyonDemo.mapper.NotMapper;
import com.otomasyon.otomasyonDemo.repository.DersAtamaRepository;
import com.otomasyon.otomasyonDemo.repository.NotRepository;
import com.otomasyon.otomasyonDemo.requestDTO.NotRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.NotResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.NotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotServiceImpl implements NotService {

    private final NotRepository notRepository;
    private final DersAtamaRepository dersAtamaRepository;
    private final NotMapper notMapper;

    @Autowired
    public NotServiceImpl(NotRepository notRepository,
                          DersAtamaRepository dersAtamaRepository,
                          NotMapper notMapper) {
        this.notRepository = notRepository;
        this.dersAtamaRepository = dersAtamaRepository;
        this.notMapper = notMapper;
    }
    @Override
    public List<NotResponseDTO> findAll() {
        return notRepository.findAll()
                .stream()
                .map(notMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<NotResponseDTO> findById(Long id) {
        return notRepository.findById(id)
                .map(notMapper::toDTO);
    }
    @Override
    public NotResponseDTO save(NotRequestDTO notDTO) {
        Not notEntity = notMapper.toEntity(notDTO);
        Not saved = notRepository.save(notEntity);
        return notMapper.toDTO(saved);
    }
    @Override
    public Optional<NotResponseDTO> update(Long id, NotRequestDTO notDTO) {
        return notRepository.findById(id)
                .map(existing -> {
                    Not updated = notMapper.toEntity(notDTO);
                    updated.setId(id);
                    return notMapper.toDTO(notRepository.save(updated));
                });
    }
    @Override
    public boolean deleteById(Long id) {
        if (notRepository.existsById(id)) {
            notRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
