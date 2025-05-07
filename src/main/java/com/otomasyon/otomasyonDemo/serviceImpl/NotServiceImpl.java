package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.entity.Not;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.NotMapper;
import com.otomasyon.otomasyonDemo.repository.DersAtamaRepository;
import com.otomasyon.otomasyonDemo.repository.NotRepository;
import com.otomasyon.otomasyonDemo.requestDTO.NotRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.NotResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.NotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public NotResponseDTO findById(Long id) {
        Not not = notRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not bulunamadı :" + id));
        return notMapper.toDTO(not);
    }

    @Override
    public NotResponseDTO save(NotRequestDTO notDTO) {
        Not not = notMapper.toEntity(notDTO);
        return notMapper.toDTO(notRepository.save(not));
    }

    @Override
    public NotResponseDTO update(Long id, NotRequestDTO notDTO) {
        Not not = notRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not bulunamadı: " + id));

        DersAtama dersAtama = notMapper.mapDersAtama(notDTO.getDersAtamaId());
        not.setDersAtama(dersAtama);

        Not updatedNot = notRepository.save(not);
        return notMapper.toDTO(updatedNot);
    }

    @Override
    public void deleteById(Long id) {
        Not not = notRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not bulunamadı: " + id));
        notRepository.delete(not);
    }
}
