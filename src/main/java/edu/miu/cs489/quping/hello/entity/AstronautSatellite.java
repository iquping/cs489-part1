package edu.miu.cs489.quping.hello.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@Entity
@Table(name = "astronaut_satellite")
public class AstronautSatellite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "astronaut_id")
    private Astronaut astronaut;

    @ManyToOne
    @JoinColumn(name = "satellite_id")
    private Satellite satellite;

    // Default constructor required by JPA
    public AstronautSatellite() {
    }

    public AstronautSatellite(Astronaut astronaut, Satellite satellite) {
        this.astronaut = astronaut;
        this.satellite = satellite;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Astronaut getAstronaut() {
        return astronaut;
    }

    public void setAstronaut(Astronaut astronaut) {
        this.astronaut = astronaut;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstronautSatellite that = (AstronautSatellite) o;
        return Objects.equals(astronaut, that.astronaut) &&
                Objects.equals(satellite, that.satellite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(astronaut, satellite);
    }
}
