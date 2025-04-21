package edu.miu.cs489.quping.hello.service;

import edu.miu.cs489.quping.hello.dto.AstronautDTO;
import edu.miu.cs489.quping.hello.dto.SatelliteDTO;
import edu.miu.cs489.quping.hello.entity.Astronaut;
import edu.miu.cs489.quping.hello.entity.AstronautSatellite;
import edu.miu.cs489.quping.hello.entity.OrbitType;
import edu.miu.cs489.quping.hello.entity.Satellite;
import edu.miu.cs489.quping.hello.exception.SatelliteDecommissionedException;
import edu.miu.cs489.quping.hello.exception.SatelliteNotFoundException;
import edu.miu.cs489.quping.hello.repository.SatelliteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */

@Service
public class SatelliteServiceImpl implements SatelliteService {

    private final SatelliteRepository satelliteRepository;

    @Autowired
    public SatelliteServiceImpl(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    @Override
    @Transactional()
    public List<SatelliteDTO> getAllSatellites() {
        return satelliteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public SatelliteDTO getSatelliteById(Long id) {
        Satellite satellite = satelliteRepository.findById(id)
                .orElseThrow(() -> new SatelliteNotFoundException("Satellite not found with id: " + id));
        return convertToDTOWithAstronauts(satellite);
    }

    @Override
    @Transactional
    public SatelliteDTO createSatellite(SatelliteDTO satelliteDTO) {
        Satellite satellite = convertToEntity(satelliteDTO);
        satellite = satelliteRepository.save(satellite);
        return convertToDTO(satellite);
    }

    @Override
    @Transactional
    public SatelliteDTO updateSatellite(Long id, SatelliteDTO satelliteDTO) {
        Satellite satellite = satelliteRepository.findById(id)
                .orElseThrow(() -> new SatelliteNotFoundException("Satellite not found with id: " + id));

        // Check if satellite is decommissioned
        if (satellite.isDecommissioned()) {
            throw new SatelliteDecommissionedException("Cannot update decommissioned satellite with id: " + id);
        }

        satellite.setName(satelliteDTO.getName());
        satellite.setLaunchDate(satelliteDTO.getLaunchDate());
        satellite.setOrbitType(OrbitType.valueOf(satelliteDTO.getOrbitType()));
        satellite.setDecommissioned(satelliteDTO.isDecommissioned());

        satellite = satelliteRepository.save(satellite);
        return convertToDTO(satellite);
    }

    @Override
    @Transactional
    public void deleteSatellite(Long id) {
        if (!satelliteRepository.existsById(id)) {
            throw new SatelliteNotFoundException("Satellite not found with id: " + id);
        }
        satelliteRepository.deleteById(id);
    }

    @Override
    @Transactional()
    public List<SatelliteDTO> getSatellitesByAstronautId(Long astronautId) {
        return satelliteRepository.findSatellitesByAstronautId(astronautId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public List<SatelliteDTO> getSatellitesByOrbitType(String orbitType) {
        try {
            OrbitType type = OrbitType.valueOf(orbitType);
            return satelliteRepository.findByOrbitType(type).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid orbit type: " + orbitType);
        }
    }

    private SatelliteDTO convertToDTO(Satellite satellite) {
        SatelliteDTO dto = new SatelliteDTO();
        dto.setId(satellite.getId());
        dto.setName(satellite.getName());
        dto.setLaunchDate(satellite.getLaunchDate());
        dto.setOrbitType(satellite.getOrbitType().toString());
        dto.setDecommissioned(satellite.isDecommissioned());
        return dto;
    }

    private SatelliteDTO convertToDTOWithAstronauts(Satellite satellite) {
        SatelliteDTO dto = convertToDTO(satellite);

        List<AstronautDTO> astronautDTOs = new ArrayList<>();
        for (AstronautSatellite as : satellite.getAstronauts()) {
            Astronaut astronaut = as.getAstronaut();

            AstronautDTO astronautDTO = new AstronautDTO();
            astronautDTO.setId(astronaut.getId());
            astronautDTO.setFirstName(astronaut.getFirstName());
            astronautDTO.setLastName(astronaut.getLastName());
            astronautDTO.setExperienceYears(astronaut.getExperienceYears());

            astronautDTOs.add(astronautDTO);
        }

        dto.setAssignedAstronauts(astronautDTOs);
        return dto;
    }

    private Satellite convertToEntity(SatelliteDTO dto) {
        Satellite satellite = new Satellite();
        satellite.setId(dto.getId());
        satellite.setName(dto.getName());
        satellite.setLaunchDate(dto.getLaunchDate());

        // Convert string orbit type to enum
        satellite.setOrbitType(OrbitType.valueOf(dto.getOrbitType()));
        satellite.setDecommissioned(dto.isDecommissioned());
        return satellite;
    }
}
