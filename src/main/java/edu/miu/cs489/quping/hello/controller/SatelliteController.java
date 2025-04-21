package edu.miu.cs489.quping.hello.controller;

import edu.miu.cs489.quping.hello.dto.SatelliteDTO;
import edu.miu.cs489.quping.hello.service.SatelliteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@RestController
@RequestMapping("/api/v1/satellites")
public class SatelliteController {

    private final SatelliteService satelliteService;

    @Autowired
    public SatelliteController(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }

    /**
     * Get all satellites
     *
     * @return List of satellites
     */
    @GetMapping
    public ResponseEntity<List<SatelliteDTO>> getAllSatellites() {
        List<SatelliteDTO> satellites = satelliteService.getAllSatellites();
        return ResponseEntity.ok(satellites);
    }

    /**
     * Get satellite by ID
     *
     * @param id Satellite ID
     * @return Satellite details with assigned astronauts
     */
    @GetMapping("/{id}")
    public ResponseEntity<SatelliteDTO> getSatelliteById(@PathVariable Long id) {
        SatelliteDTO satellite = satelliteService.getSatelliteById(id);
        return ResponseEntity.ok(satellite);
    }

    /**
     * Create a new satellite
     *
     * @param satelliteDTO Satellite data
     * @return Created satellite details
     */
    @PostMapping
    public ResponseEntity<SatelliteDTO> createSatellite(@Valid @RequestBody SatelliteDTO satelliteDTO) {
        SatelliteDTO createdSatellite = satelliteService.createSatellite(satelliteDTO);
        return new ResponseEntity<>(createdSatellite, HttpStatus.CREATED);
    }

    /**
     * Update an existing satellite
     * Operation will be denied if satellite is decommissioned
     *
     * @param id           Satellite ID
     * @param satelliteDTO Updated satellite data
     * @return Updated satellite details
     */
    @PutMapping("/{id}")
    public ResponseEntity<SatelliteDTO> updateSatellite(
            @PathVariable Long id,
            @Valid @RequestBody SatelliteDTO satelliteDTO) {
        SatelliteDTO updatedSatellite = satelliteService.updateSatellite(id, satelliteDTO);
        return ResponseEntity.ok(updatedSatellite);
    }

    /**
     * Delete a satellite
     *
     * @param id Satellite ID
     * @return Empty response with 204 status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSatellite(@PathVariable Long id) {
        satelliteService.deleteSatellite(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get satellites by orbit type
     *
     * @param orbitType Orbit type (LEO, MEO, GEO)
     * @return List of satellites with specified orbit type
     */
    @GetMapping("/orbit/{orbitType}")
    public ResponseEntity<List<SatelliteDTO>> getSatellitesByOrbitType(@PathVariable String orbitType) {
        List<SatelliteDTO> satellites = satelliteService.getSatellitesByOrbitType(orbitType);
        return ResponseEntity.ok(satellites);
    }

    /**
     * Get satellites by astronaut ID
     *
     * @param astronautId Astronaut ID
     * @return List of satellites assigned to the astronaut
     */
    @GetMapping("/astronaut/{astronautId}")
    public ResponseEntity<List<SatelliteDTO>> getSatellitesByAstronautId(@PathVariable Long astronautId) {
        List<SatelliteDTO> satellites = satelliteService.getSatellitesByAstronautId(astronautId);
        return ResponseEntity.ok(satellites);
    }
}

