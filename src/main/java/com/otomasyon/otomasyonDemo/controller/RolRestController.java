package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.requestDTO.RolRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.RolResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolRestController {

    private final RolService rolService;

    @Autowired
    public RolRestController(RolService rolService) {
        this.rolService = rolService;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<RolResponseDTO> findAll() {
        return rolService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen')")
    @GetMapping("/id/{id}")
    public RolResponseDTO getRol(@PathVariable Long id) {
        return rolService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol bulunamadı."));
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public RolResponseDTO addRol(@RequestBody RolRequestDTO rolDTO) {
        validateRolTuru(rolDTO.getRolTuru());
        return rolService.save(rolDTO);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public RolResponseDTO updateRol(@PathVariable Long id, @RequestBody RolRequestDTO rolDTO) {
        validateRolTuru(rolDTO.getRolTuru());
        RolResponseDTO mevcutRol = rolService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek rol bulunamadı."));
        return rolService.update(id, rolDTO);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteRol(@PathVariable Long id) {
        rolService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol bulunamadı."));
        rolService.deleteById(id);
        return "Rol silindi.";
    }

    private void validateRolTuru(String rolTuruStr) {
        try {
            Rol.RolTuru.valueOf(rolTuruStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Geçersiz rol türü.");
        }
    }
}
