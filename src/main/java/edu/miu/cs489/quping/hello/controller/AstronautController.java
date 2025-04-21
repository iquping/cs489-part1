package edu.miu.cs489.quping.hello.controller;

import edu.miu.cs489.quping.hello.dto.AstronautDTO;
import edu.miu.cs489.quping.hello.service.AstronautService;
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
@RequestMapping("/api/v1/astronauts")
public class AstronautController {

    private final AstronautService astronautService;

    @Autowired
    public AstronautController(AstronautService astronautService) {
        this.astronautService = astronautService;
    }

    /**
     * Get all astronauts with optional sorting
     *
     * @param sort  Sorting field (default: id)
     * @param order Sorting order (asc/desc, default: asc)
     * @return List of astronauts
     */
    @GetMapping
    public ResponseEntity<List<AstronautDTO>> getAllAstronauts(
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "asc") String order) {
        List<AstronautDTO> astronauts = astronautService.getAllAstronauts(sort, order);
        return ResponseEntity.ok(astronauts);
    }

    /**
     * Get astronaut by ID
     *
     * @param id Astronaut ID
     * @return Astronaut details with assigned satellites
     */
    @GetMapping("/{id}")
    public ResponseEntity<AstronautDTO> getAstronautById(@PathVariable Long id) {
        AstronautDTO astronaut = astronautService.getAstronautById(id);
        return ResponseEntity.ok(astronaut);
    }

    /**
     * Create a new astronaut
     *
     * @param astronautDTO Astronaut data with optional satellite IDs to assign
     * @return Created astronaut details
     */
    @PostMapping
    public ResponseEntity<AstronautDTO> createAstronaut(@Valid @RequestBody AstronautDTO astronautDTO) {
        AstronautDTO createdAstronaut = astronautService.createAstronaut(astronautDTO);
        return new ResponseEntity<>(createdAstronaut, HttpStatus.CREATED);
    }

    /**
     * Update an existing astronaut
     *
     * @param id           Astronaut ID
     * @param astronautDTO Updated astronaut data
     * @return Updated astronaut details
     */
    @PutMapping("/{id}")
    public ResponseEntity<AstronautDTO> updateAstronaut(
            @PathVariable Long id,
            @Valid @RequestBody AstronautDTO astronautDTO) {
        AstronautDTO updatedAstronaut = astronautService.updateAstronaut(id, astronautDTO);
        return ResponseEntity.ok(updatedAstronaut);
    }

    /**
     * Delete an astronaut
     *
     * @param id Astronaut ID
     * @return Empty response with 204 status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAstronaut(@PathVariable Long id) {
        astronautService.deleteAstronaut(id);
        return ResponseEntity.noContent().build();
    }
}
