package com.otomasyon.otomasyonDemo.responseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CevapResponseDTO {
    private Long id;
    private Long degerlendirmeId;
    private Long soruId;
    private int puan;
}
