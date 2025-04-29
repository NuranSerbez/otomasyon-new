package com.otomasyon.otomasyonDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.serviceInterface.OgrenciProgramiService;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import com.otomasyon.otomasyonDemo.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ogrenci_programi")
public class OgrenciProgramiRestController {
    private OgrenciProgramiService ogrenciProgramiService;
    private UserService userService;
    private ProgramService programService;
    private ObjectMapper objectMapper;

    @Autowired
    public OgrenciProgramiRestController(OgrenciProgramiService ogrenciProgramiService, UserService userService, ProgramService programService, ObjectMapper objectMapper) {
        this.ogrenciProgramiService = ogrenciProgramiService;
        this.userService = userService;
        this.programService = programService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/all")
    public List<OgrenciProgrami> findAll() {
        return ogrenciProgramiService.findAll();
    }

    @PreAuthorize("hasAnyRole('Idareci', 'Akademisyen', 'Ogrenci')")

    @GetMapping("/id/{id}")
    public OgrenciProgrami getOgrenciProgrami(@PathVariable Long id) {
        return ogrenciProgramiService.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci Programı bulunamadı - " + id));
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PostMapping("/add")
    public OgrenciProgrami addOgrenciProgrami(@RequestBody OgrenciProgrami theOgrenciProgrami) {
        theOgrenciProgrami.setId(null);
        OgrenciProgrami dbOgrenciProgrami = ogrenciProgramiService.save(theOgrenciProgrami);
        return dbOgrenciProgrami;
    }

    @PreAuthorize("hasAnyRole('Idareci','Akademisyen')")
    @PutMapping("/update/{id}")
    public OgrenciProgrami updateOgrenciProgrami(@PathVariable Long id, @RequestBody OgrenciProgrami request) {

        OgrenciProgrami ogrenciProgrami = ogrenciProgramiService.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci programı bulunamadı: " + id));

        Long userId = request.getUser().getId();
        Long programId = request.getProgram().getId();

        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı: " + userId));
        Program program = programService.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program bulunamadı: " + programId));

        ogrenciProgrami.setUser(user);
        ogrenciProgrami.setProgram(program);

        return ogrenciProgramiService.save(ogrenciProgrami);
    }


    @PreAuthorize("hasRole('ROLE_Idareci')")
    @DeleteMapping("/delete/{id}")
    public String deleteOgrenciProgrami(@PathVariable Long id) {
        ogrenciProgramiService.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci programı bulunamadı - " + id));
        ogrenciProgramiService.deleteById(id);
        return "Öğrenci programı silindi - " + id;
    }
}
