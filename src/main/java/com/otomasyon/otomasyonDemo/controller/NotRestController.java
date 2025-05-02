package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.NotRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.NotResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DersAtamaService;
import com.otomasyon.otomasyonDemo.serviceInterface.NotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/not")
public class NotRestController {
    private NotService notService;
    private DersAtamaService dersAtamaService;

    @Autowired
    public NotRestController(NotService notService, DersAtamaService dersAtamaService) {
        this.notService = notService;
        this.dersAtamaService = dersAtamaService;
    }


    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<NotResponseDTO> findAll() {
        return notService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public NotResponseDTO getNot(@PathVariable Long id) {
        return notService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not bulunamadı: " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public NotResponseDTO addNot(@RequestBody NotRequestDTO notDTO) {
        return notService.save(notDTO);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public NotResponseDTO updateNot(@PathVariable Long id, @RequestBody NotRequestDTO notDTO) {
        return notService.update(id, notDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek not bulunamadı: " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @DeleteMapping("/delete/{id}")
    public String deleteNot(@PathVariable Long id) {
        notService.findById(id)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı - " + id));
        notService.deleteById(id);
        return "Not silindi - " + id;
    }
}
