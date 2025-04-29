package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.dto.RolDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolRestController {

    private final RolService rolService;

    @Autowired
    public RolRestController(RolService rolService) {
        this.rolService = rolService;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<RolDTO> findAll() {
        return rolService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen')")
    @GetMapping("/id/{id}")
    public RolDTO getRol(@PathVariable Long id) {
        return rolService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol bulunamadı."));
    }

    @PreAuthorize("hasRole('Idareci')")
    @PostMapping("/add")
    public RolDTO addRol(@RequestBody RolDTO rolDTO) {
        return rolService.save(rolDTO);
    }

    @PreAuthorize("hasRole('Idareci')")
    @PutMapping("/update/{id}")
    public RolDTO updateRol(@PathVariable Long id, @RequestBody RolDTO rolDTO) {
        RolDTO mevcutRol = rolService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek rol bulunamadı."));

        mevcutRol.setRolTuru(rolDTO.getRolTuru());
        return rolService.update(id, mevcutRol);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteRol(@PathVariable Long id) {
        rolService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol bulunamadı."));
        rolService.deleteById(id);
        return "Rol silindi.";
    }
}
