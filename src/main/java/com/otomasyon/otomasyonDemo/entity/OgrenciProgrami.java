package com.otomasyon.otomasyonDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ogrenci_programi")
public class OgrenciProgrami {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-references")
    private User user;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonBackReference("program-reference")
    private Program program;

}