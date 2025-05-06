package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.ProgramMapper;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    @Override
    public List<ProgramResponseDTO> findAll() {
        return programRepository.findAll()
                .stream()
                .map(programMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramResponseDTO findById(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Program bulunamadı: " + id));
        return programMapper.toDTO(program);
    }

    @Override
    public ProgramResponseDTO save(ProgramRequestDTO programDTO) {
        Program program = programMapper.toEntity(programDTO);
        Program saved = programRepository.save(program);
        return programMapper.toDTO(saved);
    }

    @Override
    public ProgramResponseDTO update(Long id, ProgramRequestDTO programDTO) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Program bulunamadı: " + id));
        program.setProgramTuru(programDTO.getProgramTuru());
        Program updated = programRepository.save(program);
        return programMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Silinecek program bulunamadı: " + id));
        programRepository.delete(program);
    }
}
