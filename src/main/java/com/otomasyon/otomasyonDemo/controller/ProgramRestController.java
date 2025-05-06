package com.otomasyon.otomasyonDemo.controller;

import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/program")
@RequiredArgsConstructor
public class ProgramRestController {

    private final ProgramService programService;

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping
    public ResponseEntity<List<ProgramResponseDTO>> getAllPrograms() {
        return ResponseEntity.ok(programService.findAll());
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")
    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponseDTO> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.findById(id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping
    public ResponseEntity<ProgramResponseDTO> createProgram(@RequestBody ProgramRequestDTO programDTO) {
        ProgramResponseDTO created = programService.save(programDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponseDTO> updateProgram(@PathVariable Long id,
                                                            @RequestBody ProgramRequestDTO programDTO) {
        ProgramResponseDTO updated = programService.update(id, programDTO);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('Idareci')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
