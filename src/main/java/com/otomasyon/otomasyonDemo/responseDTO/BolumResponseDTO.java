package com.otomasyon.otomasyonDemo.responseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BolumResponseDTO {
    private Long id;
    private String bolumAdi;
    private Long fakulteId;
    private Long programId;
}
