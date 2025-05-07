package com.otomasyon.otomasyonDemo.requestDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DersAtamaRequestDTO {
    private Long dersId;
    private Long userId;
    private boolean onaydurum;
}
