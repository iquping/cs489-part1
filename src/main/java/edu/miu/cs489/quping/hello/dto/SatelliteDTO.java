package edu.miu.cs489.quping.hello.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
public class SatelliteDTO {
    private Long id;

    @NotBlank(message = "Satellite name cannot be blank")
    private String name;

    @NotNull(message = "Launch date cannot be null")
    @Past(message = "Launch date must be in the past")
    private LocalDate launchDate;

    @NotNull(message = "Orbit type cannot be null")
    @Pattern(regexp = "^(LEO|MEO|GEO)$", message = "Orbit type must be LEO, MEO, or GEO")
    private String orbitType;

    private boolean decommissioned;

    private List<AstronautDTO> assignedAstronauts;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public String getOrbitType() {
        return orbitType;
    }

    public void setOrbitType(String orbitType) {
        this.orbitType = orbitType;
    }

    public boolean isDecommissioned() {
        return decommissioned;
    }

    public void setDecommissioned(boolean decommissioned) {
        this.decommissioned = decommissioned;
    }

    public List<AstronautDTO> getAssignedAstronauts() {
        return assignedAstronauts;
    }

    public void setAssignedAstronauts(List<AstronautDTO> assignedAstronauts) {
        this.assignedAstronauts = assignedAstronauts;
    }
}
