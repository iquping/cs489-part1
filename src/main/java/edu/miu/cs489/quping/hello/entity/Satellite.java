package edu.miu.cs489.quping.hello.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@Entity
public class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @Past
    private LocalDate launchDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrbitType orbitType;

    private boolean decommissioned;

    @OneToMany(mappedBy = "satellite", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AstronautSatellite> astronauts = new HashSet<>();

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

    public OrbitType getOrbitType() {
        return orbitType;
    }

    public void setOrbitType(OrbitType orbitType) {
        this.orbitType = orbitType;
    }

    public boolean isDecommissioned() {
        return decommissioned;
    }

    public void setDecommissioned(boolean decommissioned) {
        this.decommissioned = decommissioned;
    }

    public Set<AstronautSatellite> getAstronauts() {
        return astronauts;
    }

    public void setAstronauts(Set<AstronautSatellite> astronauts) {
        this.astronauts = astronauts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Satellite satellite = (Satellite) o;
        return Objects.equals(id, satellite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
