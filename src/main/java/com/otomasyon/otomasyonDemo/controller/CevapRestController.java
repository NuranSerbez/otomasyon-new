package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.CevapRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.CevapResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.CevapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cevap")
@RequiredArgsConstructor
public class CevapRestController {

    private final CevapService cevapService;

    @GetMapping("/all")
    public ResponseEntity<List<CevapResponseDTO>> findAll() {
        List<CevapResponseDTO> cevapList = cevapService.findAll();
        return ResponseEntity.ok(cevapList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CevapResponseDTO> getById(@PathVariable Long id) {
        CevapResponseDTO cevap = cevapService.findById(id);
        if (cevap == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cevap bulunamadı: " + id);
        }
        return ResponseEntity.ok(cevap);
    }

    @PostMapping("/add")
    public ResponseEntity<CevapResponseDTO> add(@RequestBody CevapRequestDTO dto) {
        if (dto.getDegerlendirme() == null || dto.getSoru() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Değerlendirme veya soru bilgisi eksik.");
        }

        CevapResponseDTO created = cevapService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CevapResponseDTO> update(@PathVariable Long id, @RequestBody CevapRequestDTO dto) {
        if (dto.getDegerlendirme() == null || dto.getSoru() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Değerlendirme veya soru bilgisi eksik.");
        }

        CevapResponseDTO updated = cevapService.update(id, dto);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek cevap bulunamadı: " + id);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        CevapResponseDTO existing = cevapService.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek cevap bulunamadı: " + id);
        }

        cevapService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
