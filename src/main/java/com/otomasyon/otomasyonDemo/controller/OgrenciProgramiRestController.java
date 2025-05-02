package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.OgrenciProgramiRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.OgrenciProgramiResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.OgrenciProgramiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/ogrenci_programi")
public class OgrenciProgramiRestController {

    private final OgrenciProgramiService ogrenciProgramiService;

    @Autowired
    public OgrenciProgramiRestController(OgrenciProgramiService ogrenciProgramiService) {
        this.ogrenciProgramiService = ogrenciProgramiService;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<OgrenciProgramiResponseDTO> findAll() {
        return ogrenciProgramiService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/id/{id}")
    public OgrenciProgramiResponseDTO getOgrenciProgrami(@PathVariable Long id) {
        return ogrenciProgramiService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Öğrenci programı bulunamadı: " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen')")
    @PostMapping("/add")
    public OgrenciProgramiResponseDTO addOgrenciProgrami(@RequestBody OgrenciProgramiRequestDTO ogrenciProgramiDTO) {
        return ogrenciProgramiService.save(ogrenciProgramiDTO);
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen')")
    @PutMapping("/update/{id}")
    public OgrenciProgramiResponseDTO updateOgrenciProgrami(@PathVariable Long id, @RequestBody OgrenciProgramiRequestDTO ogrenciProgramiDTO) {
        return ogrenciProgramiService.update(id, ogrenciProgramiDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek öğrenci programı bulunamadı: " + id));
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public void deleteOgrenciProgrami(@PathVariable Long id) {
        boolean deleted = ogrenciProgramiService.deleteById(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek öğrenci programı bulunamadı: " + id);
        }
    }
}
