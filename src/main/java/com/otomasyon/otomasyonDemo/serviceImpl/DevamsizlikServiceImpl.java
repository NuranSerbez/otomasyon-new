package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.DersAtama;
import com.otomasyon.otomasyonDemo.entity.Devamsizlik;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.DevamsizlikMapper;
import com.otomasyon.otomasyonDemo.repository.DersAtamaRepository;
import com.otomasyon.otomasyonDemo.repository.DevamsizlikRepository;
import com.otomasyon.otomasyonDemo.requestDTO.DevamsizlikRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DevamsizlikResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DevamsizlikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevamsizlikServiceImpl implements DevamsizlikService {
    private final DevamsizlikRepository devamsizlikRepository;
    private final DevamsizlikMapper devamsizlikMapper;
    private final DersAtamaRepository dersAtamaRepository;

    @Autowired
    public DevamsizlikServiceImpl(DevamsizlikRepository devamsizlikRepository, DevamsizlikMapper devamsizlikMapper, DersAtamaRepository dersAtamaRepository) {
        this.devamsizlikRepository = devamsizlikRepository;
        this.devamsizlikMapper = devamsizlikMapper;
        this.dersAtamaRepository = dersAtamaRepository;
    }

    @Override
    public List<DevamsizlikResponseDTO> findAll() {
        return devamsizlikRepository.findAll()
                .stream()
                .map(devamsizlikMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DevamsizlikResponseDTO findById(Long id) {
        Devamsizlik devamsizlik = devamsizlikRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Devamsızlık bulunamadı: " + id));
        return devamsizlikMapper.toDTO(devamsizlik);
    }

    @Override
    public DevamsizlikResponseDTO save(DevamsizlikRequestDTO devamsizlikDTO) {
        Devamsizlik devamsizlik = devamsizlikMapper.toEntity(devamsizlikDTO);
        return devamsizlikMapper.toDTO(devamsizlikRepository.save(devamsizlik));
    }

    @Override
    public DevamsizlikResponseDTO update(Long id, DevamsizlikRequestDTO devamsizlikDTO) {
        Devamsizlik devamsizlik = devamsizlikRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Devamsızlık bulunamadı: " + id));

        DersAtama dersAtama = devamsizlikMapper.mapToDersAtama(devamsizlikDTO.getDersAtamaId());
        devamsizlik.setDersAtama(dersAtama);

        Devamsizlik updatedDevamsizlik = devamsizlikRepository.save(devamsizlik);
        return devamsizlikMapper.toDTO(updatedDevamsizlik);
    }


    @Override
    public void deleteById(Long id) {
        Devamsizlik devamsizlik = devamsizlikRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Devamsızlık bulunamadı: " + id));
        devamsizlikRepository.delete(devamsizlik);
    }
}
