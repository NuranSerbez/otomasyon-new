package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;
import com.otomasyon.otomasyonDemo.mapper.OgrenciProgramiMapper;
import com.otomasyon.otomasyonDemo.repository.OgrenciProgramiRepository;
import com.otomasyon.otomasyonDemo.requestDTO.OgrenciProgramiRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.OgrenciProgramiResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.OgrenciProgramiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OgrenciProgramiServiceImpl implements OgrenciProgramiService {

    private final OgrenciProgramiRepository ogrenciProgramiRepository;
    private final OgrenciProgramiMapper ogrenciProgramiMapper;

    @Autowired
    public OgrenciProgramiServiceImpl(OgrenciProgramiRepository ogrenciProgramiRepository,
                                      OgrenciProgramiMapper ogrenciProgramiMapper) {
        this.ogrenciProgramiRepository = ogrenciProgramiRepository;
        this.ogrenciProgramiMapper = ogrenciProgramiMapper;
    }

    @Override
    public List<OgrenciProgramiResponseDTO> findAll() {
        return ogrenciProgramiRepository.findAll().stream()
                .map(ogrenciProgramiMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OgrenciProgramiResponseDTO> findById(Long id) {
        return ogrenciProgramiRepository.findById(id)
                .map(ogrenciProgramiMapper::toDTO);
    }

    @Override
    public OgrenciProgramiResponseDTO save(OgrenciProgramiRequestDTO ogrenciProgramiDTO) {
        OgrenciProgrami ogrenciProgrami = ogrenciProgramiMapper.toEntity(ogrenciProgramiDTO);
        OgrenciProgrami saved = ogrenciProgramiRepository.save(ogrenciProgrami);
        return ogrenciProgramiMapper.toDTO(saved);
    }

    @Override
    public Optional<OgrenciProgramiResponseDTO> update(Long id, OgrenciProgramiRequestDTO ogrenciProgramiDTO) {
        return ogrenciProgramiRepository.findById(id)
                .map(existing -> {
                    OgrenciProgrami updated = ogrenciProgramiMapper.toEntity(ogrenciProgramiDTO);
                    updated.setId(existing.getId());
                    return ogrenciProgramiMapper.toDTO(ogrenciProgramiRepository.save(updated));
                });
    }

    @Override
    public boolean deleteById(Long id) {
        if (ogrenciProgramiRepository.existsById(id)) {
            ogrenciProgramiRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
