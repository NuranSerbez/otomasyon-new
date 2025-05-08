package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.UserMapper;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
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
    private final RolRepository rolRepository;


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
    public UserResponseDTO save(UserRequestDTO userDTO) {
        Rol rol = rolRepository.findById(userDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol bulunamadı"));
        User user = new User();
        user.setIsim(userDTO.getIsim());
        user.setSoyisim(userDTO.getSoyisim());
        user.setTckn(userDTO.getTckn());
        user.setEmail(userDTO.getEmail());
        user.setAdres(userDTO.getAdres());
        user.setTelefon(userDTO.getTelefon());
        user.setPassword(userDTO.getPassword());
        user.setSifreGuncelligi(userDTO.isSifreGuncelligi());
        user.setRol(rol);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + id));
        if (userDTO.getRolId() != null) {
            Rol rol = rolRepository.findById(userDTO.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol bulunamadı"));
            user.setRol(rol);
        }
        user.setIsim(userDTO.getIsim());
        user.setSoyisim(userDTO.getSoyisim());
        user.setTckn(userDTO.getTckn());
        user.setEmail(userDTO.getEmail());
        user.setAdres(userDTO.getAdres());
        user.setTelefon(userDTO.getTelefon());
        user.setPassword(userDTO.getPassword());
        user.setSifreGuncelligi(userDTO.isSifreGuncelligi());

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + id));
        userRepository.delete(user);
    }
}

