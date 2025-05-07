package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fakulte")
public class Fakulte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fakulte_adi")
    private String fakulteAdi;

    @OneToMany(mappedBy = "fakulte", fetch = FetchType.LAZY)
    @JsonManagedReference("fakulte-reference")
    private  List<Bolum> bolumlerId;
}
