package com.otomasyon.otomasyonDemo.responseDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DersAtamaResponseDTO {
    private Long id;
    private Long dersId;
    private Long userId;
    private boolean onaydurum;
}
