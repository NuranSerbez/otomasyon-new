package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sonuc")
public class Not {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vize")
    private int vize;

    @Column(name = "final")
    private int finl;

    @Column(name = "ortalama")
    private int ortalama;

    @ManyToOne
    @JoinColumn(name = "ders")
    @JsonBackReference("ders-references")
    private DersAtama dersAtama;

    @PrePersist
    @PreUpdate
    public void calculateOrtalama() {
        this.ortalama = (int) ((this.vize * 0.4) + (this.finl * 0.6));
    }
}
