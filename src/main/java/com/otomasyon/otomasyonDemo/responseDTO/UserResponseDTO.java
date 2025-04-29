package com.otomasyon.otomasyonDemo.responseDTO;

import lombok.*;

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
    private boolean sifreGuncelligi;
    private String rol; // Enum değer (String'e maplenecek)
}
