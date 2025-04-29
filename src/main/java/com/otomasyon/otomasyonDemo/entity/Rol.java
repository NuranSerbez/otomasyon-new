package com.otomasyon.otomasyonDemo.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_turu")
    private RolTuru rolTuru;


    public enum RolTuru {
        IDARECI,
        AKADEMISYEN,
        OGRENCI
    }
    public void setRolTuruFromString(String rolTuruStr) {
        try {
            this.rolTuru = RolTuru.valueOf(rolTuruStr);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Geçersiz rol türü: " + rolTuruStr);
        }
    }
}
