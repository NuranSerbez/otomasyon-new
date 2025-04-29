package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.dto.ProgramDTO;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.mapper.ProgramMapper;
import com.otomasyon.otomasyonDemo.mapper.RolMapper;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<ProgramDTO> findAll() {
        return programRepository.findAll()
                .stream()
                .map(ProgramMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProgramDTO> findById(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Program bulunamadı."));
        return Optional.of(ProgramMapper.toDTO(program));
    }

    @Override
    public ProgramDTO save(ProgramDTO programDTO) {
        Program program = ProgramMapper.toEntity(programDTO);
        Program savedProgram = programRepository.save(program);
        return ProgramMapper.toDTO(savedProgram);
    }

    @Override
    public ProgramDTO update(Long id, ProgramDTO programDTO) {
        Program program = programRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Güncellenecek program bulunamadı."));
        program.setProgramTuru(programDTO.getProgramTuru());
        Program updatedProgram = programRepository.save(program);
        return ProgramMapper.toDTO(updatedProgram);
    }

    @Override
    public void deleteById(Long id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
        }
    }
}
