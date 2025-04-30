package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.requestDTO.UserRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.UserResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;
    private final RolService rolService;

    public UserRestController(UserService userService, RolService rolService) {
        this.userService = userService;
        this.rolService = rolService;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/id/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userDTO) {
        validateRol(userDTO.getRol());
        return userService.save(userDTO);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userDTO) {
        validateRol(userDTO.getRol());
        return userService.update(id, userDTO);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.findById(id);
        userService.deleteById(id);
        return "Kullanıcı silindi.";
    }

    private void validateRol(String rolStr) {
        try {
            Rol.RolTuru rolTuru = Rol.RolTuru.valueOf(rolStr);
            rolService.findByRolTuru(rolTuru)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol bulunamadı."));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Geçersiz rol.");
        }
    }
}
