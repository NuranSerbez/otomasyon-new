package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "program_turu")
    private String programTuru;

    @OneToMany(mappedBy = "program")
    @JsonManagedReference("program-reference")
    private List<Bolum> bolumlerId;

    @OneToMany(mappedBy = "program")
    @JsonManagedReference("program-reference")
    private List<OgrenciProgrami> ogrenciProgramlariId;

}