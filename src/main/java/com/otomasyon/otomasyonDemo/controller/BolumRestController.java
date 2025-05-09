package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.BolumRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.BolumResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.BolumService;
import com.otomasyon.otomasyonDemo.serviceInterface.FakulteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bolum")
@RequiredArgsConstructor
public class BolumRestController {

    private final BolumService bolumService;
    private final FakulteService fakulteService;

    @GetMapping("/all")
    public ResponseEntity<List<BolumResponseDTO>> findAll() {
        List<BolumResponseDTO> bolumList = bolumService.findAll();
        return ResponseEntity.ok(bolumList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BolumResponseDTO> getById(@PathVariable Long id) {
        BolumResponseDTO bolum = bolumService.findById(id);
        if (bolum == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bölüm bulunamadı: " + id);
        }
        return ResponseEntity.ok(bolum);
    }

    @PostMapping("/add")
    public ResponseEntity<BolumResponseDTO> add(@RequestBody BolumRequestDTO dto) {
        if (dto.getFakulte() == null || dto.getFakulte().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fakülte bilgisi eksik veya geçersiz.");
        }

        if (dto.getProgram() == null || dto.getProgram().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Program bilgisi eksik veya geçersiz.");
        }

        BolumResponseDTO createdBolum = bolumService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBolum);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BolumResponseDTO> update(@PathVariable Long id, @RequestBody BolumRequestDTO dto) {
        if (dto.getFakulte() == null || fakulteService.findById(dto.getFakulte().getId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fakülte bulunamadı.");
        }

        BolumResponseDTO updatedBolum = bolumService.update(id, dto);
        if (updatedBolum == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek bölüm bulunamadı: " + id);
        }

        return ResponseEntity.ok(updatedBolum);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        BolumResponseDTO existing = bolumService.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek bölüm bulunamadı: " + id);
        }

        bolumService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
