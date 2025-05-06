package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Degerlendirme;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.responseDTO.UserResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DegerlendirmeService;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/degerlendirme")
public class DegerlendirmeRestController {

    private final DegerlendirmeService degerlendirmeService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public DegerlendirmeRestController(DegerlendirmeService degerlendirmeService, UserService userService, ObjectMapper objectMapper) {
        this.degerlendirmeService = degerlendirmeService;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasRole('Idareci')")
    @GetMapping("/all")
    public List<Degerlendirme> findAll() {
        return degerlendirmeService.findAll();
    }

    @PreAuthorize("hasRole('Idareci')")
    @GetMapping("/id/{id}")
    public Degerlendirme getDegerlendirme(@PathVariable Long id) {
        return degerlendirmeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Değerlendirme bulunamadı - " + id));
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public Degerlendirme addDegerlendirme(@RequestBody Degerlendirme theDegerlendirme) {
        theDegerlendirme.setId(null);
        Long akademisyenId = theDegerlendirme.getAkademisyen().getId();
        Long ogrenciId = theDegerlendirme.getOgrenci().getId();
        UserResponseDTO akademisyenOptional = userService.findById(akademisyenId);
        if (akademisyenOptional == null) {
            throw new RuntimeException("Akademisyen bulunamadı - " + akademisyenId);
        }
        User akademisyen = new User();
        akademisyen.setId(akademisyenOptional.getId());
        theDegerlendirme.setAkademisyen(akademisyen);
        UserResponseDTO ogrenciOptional = userService.findById(ogrenciId);
        if (ogrenciOptional == null) {
            throw new RuntimeException("Öğrenci bulunamadı - " + ogrenciId);
        }
        User ogrenci = new User();
        ogrenci.setId(ogrenciOptional.getId());
        theDegerlendirme.setOgrenci(ogrenci);

        return degerlendirmeService.save(theDegerlendirme);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteDegerlendirme(@PathVariable Long id) {
        degerlendirmeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Değerlendirme bulunamadı - " + id));
        degerlendirmeService.deleteById(id);
        return "Değerlendirme silindi - " + id;
    }
}
