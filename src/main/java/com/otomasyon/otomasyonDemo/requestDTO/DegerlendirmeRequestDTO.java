package com.otomasyon.otomasyonDemo.requestDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DegerlendirmeRequestDTO {
    private int ortalama;
    private Long ogrenciId;
    private Long akademisyenId;
}
