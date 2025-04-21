package edu.miu.cs489.quping.hello.service;

import edu.miu.cs489.quping.hello.dto.AstronautDTO;
import edu.miu.cs489.quping.hello.dto.SatelliteDTO;
import edu.miu.cs489.quping.hello.entity.Astronaut;
import edu.miu.cs489.quping.hello.entity.AstronautSatellite;
import edu.miu.cs489.quping.hello.entity.Satellite;
import edu.miu.cs489.quping.hello.exception.AstronautNotFoundException;
import edu.miu.cs489.quping.hello.exception.InvalidSatelliteReferenceException;
import edu.miu.cs489.quping.hello.repository.AstronautRepository;
import edu.miu.cs489.quping.hello.repository.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@Service
public class AstronautServiceImpl implements AstronautService {

    private final AstronautRepository astronautRepository;
    private final SatelliteRepository satelliteRepository;

    @Autowired
    public AstronautServiceImpl(AstronautRepository astronautRepository, SatelliteRepository satelliteRepository) {
        this.astronautRepository = astronautRepository;
        this.satelliteRepository = satelliteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AstronautDTO> getAllAstronauts(String sortBy, String sortOrder) {
        Sort.Direction direction = sortOrder != null && sortOrder.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortBy != null ? sortBy : "id");

        return astronautRepository.findAll(sort).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AstronautDTO getAstronautById(Long id) {
        Astronaut astronaut = astronautRepository.findById(id)
                .orElseThrow(() -> new AstronautNotFoundException("Astronaut not found with id: " + id));
        return convertToDTOWithSatellites(astronaut);
    }

    @Override
    @Transactional
    public AstronautDTO createAstronaut(AstronautDTO astronautDTO) {
        // Validate satellite IDs if provided
        if (astronautDTO.getSatelliteIds() != null && !astronautDTO.getSatelliteIds().isEmpty()) {
            validateSatelliteIds(astronautDTO.getSatelliteIds());
        }

        Astronaut astronaut = convertToEntity(astronautDTO);
        astronaut = astronautRepository.save(astronaut);

        // Assign satellites if IDs are provided
        if (astronautDTO.getSatelliteIds() != null && !astronautDTO.getSatelliteIds().isEmpty()) {
            assignSatellitesToAstronaut(astronaut, astronautDTO.getSatelliteIds());
        }

        return convertToDTOWithSatellites(astronaut);
    }

    @Override
    @Transactional
    public AstronautDTO updateAstronaut(Long id, AstronautDTO astronautDTO) {
        Astronaut astronaut = astronautRepository.findById(id)
                .orElseThrow(() -> new AstronautNotFoundException("Astronaut not found with id: " + id));

        astronaut.setFirstName(astronautDTO.getFirstName());
        astronaut.setLastName(astronautDTO.getLastName());
        astronaut.setExperienceYears(astronautDTO.getExperienceYears());

        // Update satellite assignments if IDs are provided
        if (astronautDTO.getSatelliteIds() != null) {
            validateSatelliteIds(astronautDTO.getSatelliteIds());

            // Clear existing assignments
            astronaut.getSatellites().clear();

            // Create new assignments
            assignSatellitesToAstronaut(astronaut, astronautDTO.getSatelliteIds());
        }

        astronaut = astronautRepository.save(astronaut);
        return convertToDTOWithSatellites(astronaut);
    }

    @Override
    @Transactional
    public void deleteAstronaut(Long id) {
        if (!astronautRepository.existsById(id)) {
            throw new AstronautNotFoundException("Astronaut not found with id: " + id);
        }
        astronautRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AstronautDTO> getAstronautsBySatelliteId(Long satelliteId) {
        return astronautRepository.findAstronautsBySatelliteId(satelliteId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper method to validate satellite IDs
    private void validateSatelliteIds(List<Long> satelliteIds) {
        for (Long satelliteId : satelliteIds) {
            if (!satelliteRepository.existsById(satelliteId)) {
                throw new InvalidSatelliteReferenceException("Satellite not found with id: " + satelliteId);
            }
        }
    }

    // Helper method to assign satellites to an astronaut
    private void assignSatellitesToAstronaut(Astronaut astronaut, List<Long> satelliteIds) {
        for (Long satelliteId : satelliteIds) {
            Satellite satellite = satelliteRepository.findById(satelliteId)
                    .orElseThrow(() -> new InvalidSatelliteReferenceException("Satellite not found with id: " + satelliteId));

            astronaut.assignToSatellite(satellite);
        }
    }

    private AstronautDTO convertToDTO(Astronaut astronaut) {
        AstronautDTO dto = new AstronautDTO();
        dto.setId(astronaut.getId());
        dto.setFirstName(astronaut.getFirstName());
        dto.setLastName(astronaut.getLastName());
        dto.setExperienceYears(astronaut.getExperienceYears());
        return dto;
    }

    private AstronautDTO convertToDTOWithSatellites(Astronaut astronaut) {
        AstronautDTO dto = convertToDTO(astronaut);

        List<SatelliteDTO> satelliteDTOs = new ArrayList<>();
        for (AstronautSatellite as : astronaut.getSatellites()) {
            Satellite satellite = as.getSatellite();

            SatelliteDTO satelliteDTO = new SatelliteDTO();
            satelliteDTO.setId(satellite.getId());
            satelliteDTO.setName(satellite.getName());
            satelliteDTO.setLaunchDate(satellite.getLaunchDate());
            satelliteDTO.setOrbitType(satellite.getOrbitType().toString());
            satelliteDTO.setDecommissioned(satellite.isDecommissioned());

            satelliteDTOs.add(satelliteDTO);
        }

        dto.setAssignedSatellites(satelliteDTOs);
        return dto;
    }

    private Astronaut convertToEntity(AstronautDTO dto) {
        Astronaut astronaut = new Astronaut();
        astronaut.setId(dto.getId());
        astronaut.setFirstName(dto.getFirstName());
        astronaut.setLastName(dto.getLastName());
        astronaut.setExperienceYears(dto.getExperienceYears());
        return astronaut;
    }
}
