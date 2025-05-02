package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.SoruRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.SoruResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.SoruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/soru")
public class SoruRestController {

    private final SoruService soruService;

    @Autowired
    public SoruRestController(SoruService soruService) {
        this.soruService = soruService;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<SoruResponseDTO> findAll() {
        return soruService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/{id}")
    public SoruResponseDTO getSoru(@PathVariable Long id) {
        return soruService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soru bulunamadı."));
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SoruResponseDTO addSoru(@RequestBody SoruRequestDTO soruDTO) {
        return soruService.save(soruDTO);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/{id}")
    public SoruResponseDTO updateSoru(@PathVariable Long id, @RequestBody SoruRequestDTO soruDTO) {
        return soruService.update(id, soruDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek soru bulunamadı."));
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteSoru(@PathVariable Long id) {
        soruService.findById(id)
                        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Soru bulunamadı"));
        soruService.deleteById(id);
        return "Soru silindi.";
    }
}
