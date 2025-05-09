package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.DevamsizlikRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DevamsizlikResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DevamsizlikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/devamsizlik")
@RequiredArgsConstructor
public class DevamsizlikRestController {

    private final DevamsizlikService devamsizlikService;

    @GetMapping("/all")
    public ResponseEntity<List<DevamsizlikResponseDTO>> findAll() {
        List<DevamsizlikResponseDTO> list = devamsizlikService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DevamsizlikResponseDTO> getById(@PathVariable Long id) {
        DevamsizlikResponseDTO response = devamsizlikService.findById(id);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Devamsızlık kaydı bulunamadı: " + id);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<DevamsizlikResponseDTO> add(@RequestBody DevamsizlikRequestDTO dto) {
        if (dto.getDersAtamaId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ders atama bilgisi eksik.");
        }

        DevamsizlikResponseDTO created = devamsizlikService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DevamsizlikResponseDTO> update(@PathVariable Long id, @RequestBody DevamsizlikRequestDTO dto) {
        if (dto.getDersAtamaId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ders atama bilgisi eksik.");
        }

        DevamsizlikResponseDTO updated = devamsizlikService.update(id, dto);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek devamsızlık kaydı bulunamadı: " + id);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        DevamsizlikResponseDTO existing = devamsizlikService.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek devamsızlık kaydı bulunamadı: " + id);
        }

        devamsizlikService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
