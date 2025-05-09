package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.DersAtamaRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DersAtamaResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DersAtamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/ders_atama")
@RequiredArgsConstructor
public class DersAtamaRestController {

    private final DersAtamaService dersAtamaService;

    @GetMapping("/all")
    public ResponseEntity<List<DersAtamaResponseDTO>> findAll() {
        List<DersAtamaResponseDTO> dersAtamaList = dersAtamaService.findAll();
        return ResponseEntity.ok(dersAtamaList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DersAtamaResponseDTO> getById(@PathVariable Long id) {
        DersAtamaResponseDTO dersAtama = dersAtamaService.findById(id);
        if (dersAtama == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ders atama bulunamadı: " + id);
        }
        return ResponseEntity.ok(dersAtama);
    }

    @PostMapping("/add")
    public ResponseEntity<DersAtamaResponseDTO> add(@RequestBody DersAtamaRequestDTO dto) {
        if (dto.getDersId() == null || dto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ders veya kullanıcı bilgisi eksik.");
        }

        DersAtamaResponseDTO created = dersAtamaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DersAtamaResponseDTO> update(@PathVariable Long id, @RequestBody DersAtamaRequestDTO dto) {
        if (dto.getDersId() == null || dto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ders veya kullanıcı bilgisi eksik.");
        }

        DersAtamaResponseDTO updated = dersAtamaService.update(id, dto);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek ders atama bulunamadı: " + id);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        DersAtamaResponseDTO existing = dersAtamaService.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek ders atama bulunamadı: " + id);
        }

        dersAtamaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
