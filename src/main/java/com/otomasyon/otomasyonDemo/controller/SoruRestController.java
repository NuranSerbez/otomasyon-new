package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/soru")
@RequiredArgsConstructor
public class SoruRestController {

    private final SoruService soruService;

    @GetMapping("/all")
    public ResponseEntity<List<SoruResponseDTO>> getAllSorular() {
        return ResponseEntity.ok(soruService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SoruResponseDTO> getSoruById(@PathVariable Long id) {
        SoruResponseDTO soru = soruService.findById(id);
        if (soru == null) {
            throw new NotFoundException("Soru bulunamadı: " + id);
        }
        return ResponseEntity.ok(soru);
    }

    @PostMapping("/add")
    public ResponseEntity<SoruResponseDTO> createSoru(@RequestBody SoruRequestDTO soruDTO) {
        SoruResponseDTO created = soruService.save(soruDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SoruResponseDTO> updateSoru(@PathVariable Long id, @RequestBody SoruRequestDTO soruDTO) {
        SoruResponseDTO updated = soruService.update(id, soruDTO);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek soru bulunamadı.");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSoru(@PathVariable Long id) {
        SoruResponseDTO soru = soruService.findById(id);
        if (soru == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soru bulunamadı.");
        }
        soruService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
