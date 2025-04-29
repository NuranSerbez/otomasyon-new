package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.dto.SoruDTO;
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
    public List<SoruDTO> findAll() {
        return soruService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/id/{id}")
    public SoruDTO getSoru(@PathVariable Long id) {
        SoruDTO dto = soruService.findById(id);
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soru bulunamadı.");
        }
        return dto;
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public SoruDTO addSoru(@RequestBody SoruDTO soruDTO) {
        return soruService.save(soruDTO);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public SoruDTO updateSoru(@PathVariable Long id, @RequestBody SoruDTO yeniSoruDTO) {
        SoruDTO mevcutSoru = soruService.findById(id);
        if (mevcutSoru == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek soru bulunamadı.");
        }

        mevcutSoru.setSorular(yeniSoruDTO.getSorular());
        return soruService.update(id, mevcutSoru);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteSoru(@PathVariable Long id) {
        SoruDTO dto = soruService.findById(id);
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soru bulunamadı.");
        }
        soruService.deleteById(id);
        return "Soru silindi.";
    }
}
