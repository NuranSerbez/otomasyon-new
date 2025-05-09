package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.entity.Rol;
import com.otomasyon.otomasyonDemo.requestDTO.RolRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.RolResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
@RequiredArgsConstructor
public class RolRestController {

    private final RolService rolService;

    @GetMapping("/all")
    public ResponseEntity<List<RolResponseDTO>> getAllRoles() {
        return ResponseEntity.ok(rolService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RolResponseDTO> getRolById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(rolService.findById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol bulunamadı: " + id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<RolResponseDTO> createRol(@RequestBody RolRequestDTO dto) {
        validateRolTuru(dto.getRolTuru());
        RolResponseDTO created = rolService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RolResponseDTO> updateRol(@PathVariable Long id, @RequestBody RolRequestDTO dto) {
        validateRolTuru(dto.getRolTuru());
        RolResponseDTO updated = rolService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        rolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void validateRolTuru(String rolTuruStr) {
        try {
            Rol.RolTuru.valueOf(rolTuruStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Geçersiz rol türü.");
        }
    }
}
