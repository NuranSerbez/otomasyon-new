package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.Fakulte;
import com.otomasyon.otomasyonDemo.requestDTO.FakulteRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.FakulteResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.FakulteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/fakulte")
public class FakulteRestController {
    private FakulteService fakulteService;
    private ObjectMapper objectMapper;

    @Autowired

    public FakulteRestController(FakulteService fakulteService, ObjectMapper objectMapper) {
        this.fakulteService = fakulteService;
        this.objectMapper = objectMapper;
    }


    @GetMapping("/all")
    public List<FakulteResponseDTO> findAll() {
       List<FakulteResponseDTO> fakulteList = fakulteService.findAll();
       return fakulteList;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FakulteResponseDTO> getFakulte(@PathVariable Long id) {
        FakulteResponseDTO fakulte = fakulteService.findById(id);
        if (fakulte == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fakülte bulunamadı: " + id);
        }
        return ResponseEntity.ok(fakulte);
    }

    @PostMapping("/add")
    public ResponseEntity<FakulteResponseDTO> addFakulte(@RequestBody FakulteRequestDTO fakulteDTO) {
        FakulteResponseDTO createdFakulte = fakulteService.save(fakulteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFakulte);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FakulteResponseDTO> updateFakulte(@PathVariable Long id, @RequestBody FakulteRequestDTO fakulteDTO) {
        FakulteResponseDTO updatedFakulte = fakulteService.update(id, fakulteDTO);
        if (updatedFakulte == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Güncellenecek fakülte bulunamadı: " + id);
        }
        return ResponseEntity.ok(updatedFakulte);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFakulte(@PathVariable Long id) {
        FakulteResponseDTO fakulte = fakulteService.findById(id);
        if (fakulte == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Silinecek fakülte bulunamadı: " + id);
        }
        fakulteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}