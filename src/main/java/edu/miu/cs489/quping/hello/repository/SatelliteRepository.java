package edu.miu.cs489.quping.hello.repository;

import edu.miu.cs489.quping.hello.entity.OrbitType;
import edu.miu.cs489.quping.hello.entity.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Long> {

    /**
     * Find satellite by name (exact match)
     *
     * @param name Satellite name
     * @return Optional containing satellite if found
     */
    Optional<Satellite> findByName(String name);

    /**
     * Find satellites by orbit type
     *
     * @param orbitType Orbit type (LEO, MEO, GEO)
     * @return List of satellites with matching orbit type
     */
    List<Satellite> findByOrbitType(OrbitType orbitType);

    /**
     * Find satellites by decommissioned status
     *
     * @param decommissioned Decommissioned status
     * @return List of satellites with matching decommissioned status
     */
    List<Satellite> findByDecommissioned(boolean decommissioned);

    /**
     * Find satellites with launch date after given date
     *
     * @param date Reference date
     * @return List of satellites launched after given date
     */
    List<Satellite> findByLaunchDateAfter(LocalDate date);

    /**
     * Find satellites assigned to a specific astronaut
     *
     * @param astronautId Astronaut ID
     * @return List of satellites assigned to the astronaut
     */
    @Query("SELECT s FROM Satellite s JOIN s.astronauts a WHERE a.astronaut.id = :astronautId")
    List<Satellite> findSatellitesByAstronautId(@Param("astronautId") Long astronautId);
}

