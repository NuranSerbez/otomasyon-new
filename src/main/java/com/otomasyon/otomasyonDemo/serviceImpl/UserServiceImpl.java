package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.UserMapper;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.requestDTO.UserRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.UserResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + id));
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO save(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        if (dto.getRol() != null) {
            user.setRol(userMapper.mapStringToRol(dto.getRol()));
        }

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + id));
        if (dto.getRol() != null) {
            Rol rol = userMapper.mapStringToRol(dto.getRol());
            user.setRol(rol);
        }
        return userMapper.toDTO(userRepository.save(user));
    }


    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + id));
        userRepository.delete(user);
    }
}

