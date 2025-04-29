package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.dto.ProgramDTO;
import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.serviceInterface.BolumService;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/program")
public class ProgramRestController {
    private ProgramService programService;
    private BolumService bolumService;

    @Autowired
    public ProgramRestController(ProgramService programService, BolumService bolumService) {
        this.programService = programService;
        this.bolumService = bolumService;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<ProgramDTO> findAll() {
        return programService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public ProgramDTO getProgram(@PathVariable Long id) {
        return programService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program bulunmadı."));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public ProgramDTO addProgram(@RequestBody ProgramDTO programDTO) {
        return programService.save(programDTO);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public ProgramDTO updateProgram(@PathVariable Long id, @RequestBody ProgramDTO programDTO) {
       ProgramDTO mevcutProgram = programService.findById(id)
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mevcut program bulunamadı."));
       mevcutProgram.setProgramTuru(programDTO.getProgramTuru());
       return programService.update(id, mevcutProgram);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable Long id) {
        programService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program bulunamadı."));
        programService.deleteById(id);
        return "Program silindi.";
    }
}
