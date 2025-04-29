package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.requestDTO.UserRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.UserResponseDTO;
import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.mapper.UserMapper;
import com.otomasyon.otomasyonDemo.repository.RolRepository;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RolRepository rolRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.userMapper = userMapper;
    }

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kullanıcı bulunamadı."));
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setRol(findRolByString(userDTO.getRol()));
        user.setPassword(userDTO.getPassword());
        user.setSifreGuncelligi(userDTO.isSifreGuncelligi());

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek kullanıcı bulunamadı."));

        user.setIsim(userDTO.getIsim());
        user.setSoyisim(userDTO.getSoyisim());
        user.setTckn(userDTO.getTckn());
        user.setEmail(userDTO.getEmail());
        user.setAdres(userDTO.getAdres());
        user.setTelefon(userDTO.getTelefon());
        user.setPassword(userDTO.getPassword());
        user.setSifreGuncelligi(userDTO.isSifreGuncelligi());
        user.setRol(findRolByString(userDTO.getRol()));

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek kullanıcı bulunamadı."));
        userRepository.delete(user);
    }

    private Rol findRolByString(String rolStr) {
        try {
            Rol.RolTuru rolTuru = Rol.RolTuru.valueOf(rolStr);
            return rolRepository.findByRolTuru(rolTuru)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol bulunamadı."));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Geçersiz rol.");
        }
    }
}
