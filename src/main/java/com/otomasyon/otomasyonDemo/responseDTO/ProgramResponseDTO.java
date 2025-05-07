package com.otomasyon.otomasyonDemo.responseDTO;

import com.otomasyon.otomasyonDemo.entity.Bolum;
import com.otomasyon.otomasyonDemo.entity.OgrenciProgrami;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponseDTO {
    private Long id;
    private String programTuru;
    private List<Bolum> bolumlerId;
    private List<OgrenciProgrami> ogrenciProgramlariId;
}
