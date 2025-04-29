package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ders")
public class Ders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ders_adi")
    private String dersAdi;

    @Column(name = "kontenjan")
    private int kontenjan;

    @ManyToOne
    @JoinColumn(name = "program")
    @JsonBackReference("program-references")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "akademisyen")
    @JsonBackReference("user-refences")
    private User akademisyen;
}
