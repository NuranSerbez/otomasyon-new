package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "degerlendirme")
public class Degerlendirme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ortalama")
    private int ortalama;

    @ManyToOne
    @JoinColumn(name = "ogrenci")
    @JsonBackReference("user-references")
    private User ogrenci;

    @ManyToOne
    @JoinColumn(name = "akademisyen")
    @JsonBackReference("user-references")
    private User akademisyen;
}
