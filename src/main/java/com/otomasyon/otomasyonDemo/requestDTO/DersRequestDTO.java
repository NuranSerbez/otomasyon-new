package com.otomasyon.otomasyonDemo.requestDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DersRequestDTO {
    private String dersAdi;
    private int kontenjan;
    private Long programId;
    private Long userId;
}
