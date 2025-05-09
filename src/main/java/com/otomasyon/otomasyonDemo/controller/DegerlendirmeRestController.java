package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.DegerlendirmeRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DegerlendirmeResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DegerlendirmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/degerlendirme")
@RequiredArgsConstructor
public class DegerlendirmeRestController {

    private final DegerlendirmeService degerlendirmeService;

    @GetMapping("/all")
    public ResponseEntity<List<DegerlendirmeResponseDTO>> findAll() {
        List<DegerlendirmeResponseDTO> degerlendirmeList = degerlendirmeService.findAll();
        return ResponseEntity.ok(degerlendirmeList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DegerlendirmeResponseDTO> getById(@PathVariable Long id) {
        DegerlendirmeResponseDTO degerlendirme = degerlendirmeService.findById(id);
        if (degerlendirme == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Değerlendirme bulunamadı: " + id);
        }
        return ResponseEntity.ok(degerlendirme);
    }

    @PostMapping("/add")
    public ResponseEntity<DegerlendirmeResponseDTO> add(@RequestBody DegerlendirmeRequestDTO dto) {
        if (dto.getOgrenciId() == null || dto.getAkademisyenId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Öğrenci veya akademisyen bilgisi eksik.");
        }

        DegerlendirmeResponseDTO created = degerlendirmeService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DegerlendirmeResponseDTO> update(@PathVariable Long id, @RequestBody DegerlendirmeRequestDTO dto) {
        if (dto.getOgrenciId() == null || dto.getAkademisyenId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Öğrenci veya akademisyen bilgisi eksik.");
        }

        DegerlendirmeResponseDTO updated = degerlendirmeService.update(id, dto);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek değerlendirme bulunamadı: " + id);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        DegerlendirmeResponseDTO existing = degerlendirmeService.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek değerlendirme bulunamadı: " + id);
        }

        degerlendirmeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
