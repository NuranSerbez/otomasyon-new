package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/program")
public class ProgramRestController {

    private final ProgramService programService;

    @Autowired
    public ProgramRestController(ProgramService programService) {
        this.programService = programService;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/all")
    public List<ProgramResponseDTO> findAll() {
        return programService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/{id}")
    public ProgramResponseDTO getProgram(@PathVariable Long id) {
        return programService.findById(id)
                .map(program -> program)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program bulunamadı."));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramResponseDTO addProgram(@RequestBody ProgramRequestDTO programDTO) {
        return programService.save(programDTO);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/{id}")
    public ProgramResponseDTO updateProgram(@PathVariable Long id, @RequestBody ProgramRequestDTO programDTO) {
        return programService.update(id, programDTO)
                .map(updatedProgram -> updatedProgram)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek program bulunamadı."));
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProgram(@PathVariable Long id) {
        boolean deleted = programService.deleteById(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Program bulunamadı.");
        }
    }
}
