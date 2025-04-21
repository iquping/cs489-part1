package edu.miu.cs489.quping.hello.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@Entity
public class Astronaut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @Min(0)
    @Max(50)
    private int experienceYears;

    // Astronaut owns the relationship
    @OneToMany(mappedBy = "astronaut", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AstronautSatellite> satellites = new HashSet<>();

    // Convenience method to add a satellite assignment
    public void assignToSatellite(Satellite satellite) {
        AstronautSatellite assignment = new AstronautSatellite(this, satellite);
        satellites.add(assignment);
        satellite.getAstronauts().add(assignment);
    }

    // Convenience method to remove a satellite assignment
    public void removeFromSatellite(Satellite satellite) {
        for (Iterator<AstronautSatellite> iterator = satellites.iterator(); iterator.hasNext();) {
            AstronautSatellite assignment = iterator.next();
            if (assignment.getAstronaut().equals(this) && assignment.getSatellite().equals(satellite)) {
                iterator.remove();
                assignment.getSatellite().getAstronauts().remove(assignment);
                assignment.setAstronaut(null);
                assignment.setSatellite(null);
            }
        }
    }

    // Getters and Setters
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

    public Set<AstronautSatellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(Set<AstronautSatellite> satellites) {
        this.satellites = satellites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Astronaut astronaut = (Astronaut) o;
        return Objects.equals(id, astronaut.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
