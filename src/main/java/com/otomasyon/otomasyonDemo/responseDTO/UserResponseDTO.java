package com.otomasyon.otomasyonDemo.responseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String isim;
    private String soyisim;
    private String tckn;
    private String email;
    private String adres;
    private String telefon;
    private String rol;
    private boolean sifreGuncelligi;
    private List<OgrenciProgramiResponseDTO> ogrenciProgramlari;
}