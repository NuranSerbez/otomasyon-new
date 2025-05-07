package com.otomasyon.otomasyonDemo.requestDTO;

import com.otomasyon.otomasyonDemo.entity.Bolum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakulteRequestDTO {
    private String fakulteAdi;
    private  List<Bolum> bolumlerId;
}