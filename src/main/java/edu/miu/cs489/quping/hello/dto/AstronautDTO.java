package edu.miu.cs489.quping.hello.dto;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
// Updated DTOs with Validation
import jakarta.validation.constraints.*;
import java.util.List;

public class AstronautDTO {
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String lastName;

    @Min(value = 0, message = "Experience years cannot be negative")
    @Max(value = 50, message = "Experience years cannot exceed 50")
    private int experienceYears;

    private List<SatelliteDTO> assignedSatellites;

    // For creating astronauts with satellite assignments
    private List<Long> satelliteIds;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public List<SatelliteDTO> getAssignedSatellites() {
        return assignedSatellites;
    }

    public void setAssignedSatellites(List<SatelliteDTO> assignedSatellites) {
        this.assignedSatellites = assignedSatellites;
    }

    public List<Long> getSatelliteIds() {
        return satelliteIds;
    }

    public void setSatelliteIds(List<Long> satelliteIds) {
        this.satelliteIds = satelliteIds;
    }
}
