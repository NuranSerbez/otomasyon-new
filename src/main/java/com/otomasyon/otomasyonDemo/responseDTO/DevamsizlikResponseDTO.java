package com.otomasyon.otomasyonDemo.responseDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevamsizlikResponseDTO {
    private Long id;
    private int toplamSaat;
    private Long dersAtamaId;
}
