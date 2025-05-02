package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.mapper.ProgramMapper;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import com.otomasyon.otomasyonDemo.requestDTO.ProgramRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.ProgramResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository, ProgramMapper programMapper) {
        this.programRepository = programRepository;
        this.programMapper = programMapper;
    }

    @Override
    public List<ProgramResponseDTO> findAll() {
        return programRepository.findAll()
                .stream()
                .map(programMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProgramResponseDTO> findById(Long id) {
        return programRepository.findById(id)
                .map(programMapper::toDTO);
    }

    @Override
    public ProgramResponseDTO save(ProgramRequestDTO programDTO) {
        Program program = programMapper.toEntity(programDTO);
        Program saved = programRepository.save(program);
        return programMapper.toDTO(saved);
    }

    @Override
    public Optional<ProgramResponseDTO> update(Long id, ProgramRequestDTO programDTO) {
        return programRepository.findById(id).map(program -> {
            program.setProgramTuru(programDTO.getProgramTuru());
            Program updated = programRepository.save(program);
            return programMapper.toDTO(updated);
        });
    }

    @Override
    public boolean deleteById(Long id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
            return true;
        }
        return false;
    }
}