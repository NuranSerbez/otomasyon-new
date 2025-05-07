package com.otomasyon.otomasyonDemo.requestDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CevapRequestDTO {
    private int puan;
    private String degerlendirme;
    private String soru;
}
