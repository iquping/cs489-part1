package edu.miu.cs489.quping.hello.service;

import edu.miu.cs489.quping.hello.dto.SatelliteDTO;

import java.util.List;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
public interface SatelliteService {
    List<SatelliteDTO> getAllSatellites();
    SatelliteDTO getSatelliteById(Long id);
    SatelliteDTO createSatellite(SatelliteDTO satelliteDTO);
    SatelliteDTO updateSatellite(Long id, SatelliteDTO satelliteDTO);
    void deleteSatellite(Long id);
    List<SatelliteDTO> getSatellitesByAstronautId(Long astronautId);
    List<SatelliteDTO> getSatellitesByOrbitType(String orbitType);
}
