package com.otomasyon.otomasyonDemo.responseDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DersResponseDTO {
    private Long id;
    private String dersAdi;
    private int kontenjan;
    private Long programId;
    private Long userId;
}
