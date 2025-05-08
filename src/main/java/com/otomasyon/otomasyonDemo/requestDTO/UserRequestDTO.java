package com.otomasyon.otomasyonDemo.requestDTO;

import com.otomasyon.otomasyonDemo.responseDTO.RolResponseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String isim;
    private String soyisim;
    private String tckn;
    private String email;
    private String adres;
    private String telefon;
    private String password;
    private Long rolId;
    private boolean sifreGuncelligi;

}
