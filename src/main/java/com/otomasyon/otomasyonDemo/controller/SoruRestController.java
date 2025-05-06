package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/soru")
@RequiredArgsConstructor
public class SoruRestController {

    private final SoruService soruService;

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping
    public ResponseEntity<List<SoruResponseDTO>> getAllSorular() {
        return ResponseEntity.ok(soruService.findAll());
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/{id}")
    public ResponseEntity<SoruResponseDTO> getSoruById(@PathVariable Long id) {
        SoruResponseDTO soru = soruService.findById(id);
        if (soru == null) {
            throw new NotFoundException("Soru bulunamadı: " + id);
        }
        return ResponseEntity.ok(soru);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping
    public ResponseEntity<SoruResponseDTO> createSoru(@RequestBody SoruRequestDTO soruDTO) {
        SoruResponseDTO created = soruService.save(soruDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/{id}")
    public ResponseEntity<SoruResponseDTO> updateSoru(@PathVariable Long id, @RequestBody SoruRequestDTO soruDTO) {
        SoruResponseDTO updated = soruService.update(id, soruDTO);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek soru bulunamadı.");
        }
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoru(@PathVariable Long id) {
        SoruResponseDTO soru = soruService.findById(id);
        if (soru == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soru bulunamadı.");
        }
        soruService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
