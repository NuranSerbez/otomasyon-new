package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.RolMapper;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
import com.otomasyon.otomasyonDemo.requestDTO.RolRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.RolResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Override
    public List<RolResponseDTO> findAll() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RolResponseDTO findById(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rol bulunamadı: " + id));
        return rolMapper.toDTO(rol);
    }

    @Override
    public RolResponseDTO save(RolRequestDTO dto) {
        Rol rol = rolMapper.toEntity(dto);
        return rolMapper.toDTO(rolRepository.save(rol));
    }

    @Override
    public RolResponseDTO update(Long id, RolRequestDTO dto) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Güncellenecek rol bulunamadı: " + id));
        rol.setRolTuru(Rol.RolTuru.valueOf(dto.getRolTuru()));
        return rolMapper.toDTO(rolRepository.save(rol));
    }

    @Override
    public void deleteById(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Silinecek rol bulunamadı: " + id));
        rolRepository.delete(rol);
    }

    @Override
    public Optional<Rol> findByRolTuru(Rol.RolTuru rolTuru) {
        return Optional.ofNullable(rolRepository.findByRolTuru(rolTuru)
                .orElseThrow(() -> new NotFoundException("Rol türü bulunamadı: " + rolTuru)));
    }
}
