package com.otomasyon.otomasyonDemo.serviceImpl;

import com.otomasyon.otomasyonDemo.entity.Ders;
import com.otomasyon.otomasyonDemo.entity.Program;
import com.otomasyon.otomasyonDemo.entity.User;
import com.otomasyon.otomasyonDemo.exception.NotFoundException;
import com.otomasyon.otomasyonDemo.mapper.DersMapper;
import com.otomasyon.otomasyonDemo.repository.DersRepository;
import com.otomasyon.otomasyonDemo.repository.ProgramRepository;
import com.otomasyon.otomasyonDemo.repository.UserRepository;
import com.otomasyon.otomasyonDemo.requestDTO.DersRequestDTO;
import com.otomasyon.otomasyonDemo.responseDTO.DersResponseDTO;
import com.otomasyon.otomasyonDemo.serviceInterface.DersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DersServiceImpl implements DersService {
    private final DersRepository dersRepository;
    private final DersMapper dersMapper;
    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    @Autowired
    public DersServiceImpl(DersRepository dersRepository, DersMapper dersMapper, ProgramRepository programRepository, UserRepository userRepository) {
        this.dersRepository = dersRepository;
        this.dersMapper = dersMapper;
        this.programRepository = programRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DersResponseDTO> findAll() {

        return dersRepository.findAll()
                .stream()
                .map(dersMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DersResponseDTO findById(Long id) {
        Ders ders = dersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ders bulunamadı: " + id));
        return dersMapper.toDTO(ders);
    }

        @Override
        public DersResponseDTO save(DersRequestDTO dersDTO) {
            Ders ders = dersMapper.toEntity(dersDTO);
            return dersMapper.toDTO(dersRepository.save(ders));
        }

    @Override
    public DersResponseDTO update(Long id, DersRequestDTO dersDTO) {
        Ders ders = dersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ders bulunamadı: " + id));

        Program program = dersMapper.mapProgram(dersDTO.getProgramId());
        ders.setProgram(program);
        User user = dersMapper.mapUser(dersDTO.getUserId());
        ders.setAkademisyen(user);
        Ders updatedDers = dersRepository.save(ders);
        return dersMapper.toDTO(updatedDers);
    }

    @Override
    public void deleteById(Long id) {
        Ders ders = dersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ders bulunamadı: " + id));
        dersRepository.delete(ders);
    }
}
