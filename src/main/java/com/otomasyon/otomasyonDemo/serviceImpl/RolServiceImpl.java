package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.dto.RolDTO;
import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.mapper.RolMapper;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {
    private RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<RolDTO> findAll() {
        return rolRepository.findAll()
                .stream()
                .map(RolMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RolDTO> findById(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı."));
        return Optional.of(RolMapper.toDTO(rol));
    }

    @Override
    public RolDTO save(RolDTO rolDTO) {
        Rol rol = RolMapper.toEntity(rolDTO);
        Rol savedRol = rolRepository.save(rol);
        return RolMapper.toDTO(savedRol);
    }

    @Override
    public RolDTO update(Long id, RolDTO rolDTO) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Güncellenecek rol bulunamadı."));
        rol.setRolTuru(Rol.RolTuru.valueOf(rolDTO.getRolTuru()));
        Rol updatedRol = rolRepository.save(rol);
        return RolMapper.toDTO(updatedRol);
    }

    @Override
    public void deleteById(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Object> findByRolTuru(Rol.RolTuru rolTuru) {
        return Optional.ofNullable(rolRepository.findByRolTuru(rolTuru));
    }
}
