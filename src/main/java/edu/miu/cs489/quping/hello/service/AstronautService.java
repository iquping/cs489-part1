package edu.miu.cs489.quping.hello.service;

import edu.miu.cs489.quping.hello.dto.AstronautDTO;

import java.util.List;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
public interface AstronautService {
    List<AstronautDTO> getAllAstronauts(String sortBy, String sortOrder);
    AstronautDTO getAstronautById(Long id);
    AstronautDTO createAstronaut(AstronautDTO astronautDTO);
    AstronautDTO updateAstronaut(Long id, AstronautDTO astronautDTO);
    void deleteAstronaut(Long id);
    List<AstronautDTO> getAstronautsBySatelliteId(Long satelliteId);
}
