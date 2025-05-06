package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.OgrenciProgramiRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.OgrenciProgramiResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.OgrenciProgramiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/ogrenci_programi")
@RequiredArgsConstructor
public class OgrenciProgramiRestController {

    private final OgrenciProgramiService ogrenciProgramiService;

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public ResponseEntity<List<OgrenciProgramiResponseDTO>> findAll() {
        List<OgrenciProgramiResponseDTO> ogrenciProgramiList = ogrenciProgramiService.findAll();
        return ResponseEntity.ok(ogrenciProgramiList);
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/id/{id}")
    public ResponseEntity<OgrenciProgramiResponseDTO> getOgrenciProgrami(@PathVariable Long id) {
        OgrenciProgramiResponseDTO ogrenciProgrami = ogrenciProgramiService.findById(id);
        if (ogrenciProgrami == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Öğrenci programı bulunamadı: " + id);
        }
        return ResponseEntity.ok(ogrenciProgrami);
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen')")
    @PostMapping("/add")
    public ResponseEntity<OgrenciProgramiResponseDTO> addOgrenciProgrami(@RequestBody OgrenciProgramiRequestDTO ogrenciProgramiDTO) {
        OgrenciProgramiResponseDTO createdOgrenciProgrami = ogrenciProgramiService.save(ogrenciProgramiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOgrenciProgrami);
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen')")
    @PutMapping("/update/{id}")
    public ResponseEntity<OgrenciProgramiResponseDTO> updateOgrenciProgrami(@PathVariable Long id, @RequestBody OgrenciProgramiRequestDTO ogrenciProgramiDTO) {
        OgrenciProgramiResponseDTO updatedOgrenciProgrami = ogrenciProgramiService.update(id, ogrenciProgramiDTO);
        if (updatedOgrenciProgrami == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek öğrenci programı bulunamadı: " + id);
        }
        return ResponseEntity.ok(updatedOgrenciProgrami);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOgrenciProgrami(@PathVariable Long id) {
        ogrenciProgramiService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}