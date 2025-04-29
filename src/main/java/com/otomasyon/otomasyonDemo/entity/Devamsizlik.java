package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "devamsizlik")
public class Devamsizlik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "toplam_saat")
    private int toplamSaat;

    @ManyToOne
    @JoinColumn(name = "ders")
    @JsonBackReference("dersAtama-references")
    private DersAtama dersAtama;
}
