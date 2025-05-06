package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.NotRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.NotResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.NotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/not")
@RequiredArgsConstructor
public class NotRestController {

    private final NotService notService;

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public ResponseEntity<List<NotResponseDTO>> findAll() {
        List<NotResponseDTO> notList = notService.findAll();
        return ResponseEntity.ok(notList);
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/id/{id}")
    public ResponseEntity<NotResponseDTO> getNot(@PathVariable Long id) {
        NotResponseDTO notResponseDTO = notService.findById(id);
        if (notResponseDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not bulunamadı: " + id);
        }
        return ResponseEntity.ok(notResponseDTO);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public ResponseEntity<NotResponseDTO> addNot(@RequestBody NotRequestDTO notDTO) {
        NotResponseDTO createdNot = notService.save(notDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNot);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public ResponseEntity<NotResponseDTO> updateNot(@PathVariable Long id, @RequestBody NotRequestDTO notDTO) {
        NotResponseDTO updatedNot = notService.update(id, notDTO);
        if (updatedNot == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek not bulunamadı: " + id);
        }
        return ResponseEntity.ok(updatedNot);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNot(@PathVariable Long id) {
        notService.findById(id);
        notService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
