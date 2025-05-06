package com.otomasyon.otomasyonDemo.responseDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DegerlendirmeResponseDTO {
    private Long id;
    private int ortalama;
    private Long ogrenciId;
    private Long akademisyenId;
}
