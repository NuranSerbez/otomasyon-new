package com.otomasyon.otomasyonDemo.responseDTO;

import com.otomasyon.otomasyonDemo.entity.Bolum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakulteResponseDTO {
    private Long id;
    private String fakulteAdi;
    private  List<Bolum> bolumlerId;

}
