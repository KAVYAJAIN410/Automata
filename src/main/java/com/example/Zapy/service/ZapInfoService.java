package com.example.Zapy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Zapy.DTO.ZapDTO;
import com.example.Zapy.Mapper.ZapMapper;
import com.example.Zapy.Repository.ZapRepository;
import com.example.Zapy.model.Zap;

@Service
public class ZapInfoService {

    private final ZapRepository zapRepository;

    public ZapInfoService(ZapRepository zapRepository) {
        this.zapRepository = zapRepository;
    }

    public Optional<ZapDTO> getZap(String id) {
        Optional<Zap> zap = zapRepository.findById(id);
        return zap.map(ZapMapper::toDTO);
    }
}
