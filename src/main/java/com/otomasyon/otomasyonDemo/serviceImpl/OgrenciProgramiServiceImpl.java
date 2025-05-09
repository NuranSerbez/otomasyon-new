package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.OgrenciProgramiMapper;
import com.otomasyon.otomasyonDemo.repository.OgrenciProgramiRepository;
import com.otomasyon.otomasyonDemo.requestDTO.OgrenciProgramiRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.OgrenciProgramiResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.OgrenciProgramiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public OgrenciProgramiResponseDTO findById(Long id) {
        OgrenciProgrami ogrenciProgrami = ogrenciProgramiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Öğrenci programı bulunamadı: " + id));
        return ogrenciProgramiMapper.toDTO(ogrenciProgrami);
    }

    @Override
    public OgrenciProgramiResponseDTO save(OgrenciProgramiRequestDTO ogrenciProgramiDTO) {
        OgrenciProgrami ogrenciProgrami = ogrenciProgramiMapper.toEntity(ogrenciProgramiDTO);
        OgrenciProgrami saved = ogrenciProgramiRepository.save(ogrenciProgrami);
        return ogrenciProgramiMapper.toDTO(saved);
    }

    @Override
    public OgrenciProgramiResponseDTO update(Long id, OgrenciProgramiRequestDTO ogrenciProgramiDTO) {
        OgrenciProgrami ogrenciProgrami = ogrenciProgramiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Güncellenecek öğrenci programı bulunamadı: " + id));

        OgrenciProgrami updated = ogrenciProgramiMapper.toEntity(ogrenciProgramiDTO);
        updated.setId(ogrenciProgrami.getId());
        OgrenciProgrami saved = ogrenciProgramiRepository.save(updated);
        return ogrenciProgramiMapper.toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        OgrenciProgrami ogrenciProgrami = ogrenciProgramiRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Silinecek öğrenci programı bulunamadı : " +id));
        ogrenciProgramiRepository.delete(ogrenciProgrami);
    }
}
