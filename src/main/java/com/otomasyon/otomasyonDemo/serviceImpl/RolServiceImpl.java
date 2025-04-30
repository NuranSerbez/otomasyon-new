package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.mapper.RolMapper;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
import com.otomasyon.otomasyonDemo.requestDTO.RolRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.RolResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    public List<RolResponseDTO> findAll() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RolResponseDTO> findById(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı."));
        return Optional.of(rolMapper.toDTO(rol));
    }

    @Override
    public RolResponseDTO save(RolRequestDTO rolDTO) {
        Rol rol = rolMapper.toEntity(rolDTO);
        Rol savedRol = rolRepository.save(rol);
        return rolMapper.toDTO(savedRol);
    }

    @Override
    public RolResponseDTO update(Long id, RolRequestDTO rolDTO) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Güncellenecek rol bulunamadı."));
        rol.setRolTuru(Rol.RolTuru.valueOf(rolDTO.getRolTuru()));
        Rol updatedRol = rolRepository.save(rol);
        return rolMapper.toDTO(updatedRol);
    }

    @Override
    public void deleteById(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Rol> findByRolTuru(Rol.RolTuru rolTuru) {
        return Optional.ofNullable(rolRepository.findByRolTuru(rolTuru));
    }
}
