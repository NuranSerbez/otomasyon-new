package com.otomasyon.otomasyonDemo.requestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.FakulteResponseDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BolumRequestDTO {
    private String bolumAdi;
    private FakulteResponseDTO fakulte;
    private ProgramResponseDTO program;
}
