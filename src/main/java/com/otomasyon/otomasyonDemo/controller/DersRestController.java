package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.DersRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DersResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/ders")
@RequiredArgsConstructor
public class DersRestController {

    private final DersService dersService;

    @GetMapping("/all")
    public ResponseEntity<List<DersResponseDTO>> findAll() {
        List<DersResponseDTO> dersList = dersService.findAll();
        return ResponseEntity.ok(dersList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DersResponseDTO> getById(@PathVariable Long id) {
        DersResponseDTO ders = dersService.findById(id);
        if (ders == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ders bulunamadı: " + id);
        }
        return ResponseEntity.ok(ders);
    }

    @PostMapping("/add")
    public ResponseEntity<DersResponseDTO> add(@RequestBody DersRequestDTO dto) {
        if (dto.getProgramId() == null || dto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Program veya kullanıcı bilgisi eksik.");
        }

        DersResponseDTO created = dersService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DersResponseDTO> update(@PathVariable Long id, @RequestBody DersRequestDTO dto) {
        if (dto.getProgramId() == null || dto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Program veya kullanıcı bilgisi eksik.");
        }

        DersResponseDTO updated = dersService.update(id, dto);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek ders bulunamadı: " + id);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        DersResponseDTO existing = dersService.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek ders bulunamadı: " + id);
        }

        dersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
