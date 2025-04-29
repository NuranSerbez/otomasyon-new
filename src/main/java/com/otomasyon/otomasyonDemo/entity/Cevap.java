package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cevap")
public class Cevap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "degerlendirme")
    @JsonBackReference("degerlendirme-references")
    private Degerlendirme degerlendirme;

    @ManyToOne
    @JoinColumn(name = "soru")
    @JsonBackReference ("soru-references")
    private Soru soru;

    @Column(name = "puan")
    private int puan;

}