package com.otomasyon.otomasyonDemo.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
    private String tckn;
    private String rol;
}
