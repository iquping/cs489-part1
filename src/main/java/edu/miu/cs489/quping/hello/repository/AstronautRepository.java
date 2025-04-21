package edu.miu.cs489.quping.hello.repository;

import edu.miu.cs489.quping.hello.entity.Astronaut;
import edu.miu.cs489.quping.hello.entity.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@Repository
public interface AstronautRepository extends JpaRepository<Astronaut, Long> {

    /**
     * Find astronauts by satellite ID
     *
     * @param satelliteId Satellite ID
     * @return List of astronauts assigned to the satellite
     */
    @Query("SELECT a FROM Astronaut a JOIN a.satellites s WHERE s.satellite.id = :satelliteId")
    List<Astronaut> findAstronautsBySatelliteId(@Param("satelliteId") Long satelliteId);

    /**
     * Find astronauts by experience years greater than or equal to given value
     *
     * @param minExperience Minimum experience years
     * @return List of astronauts with experience >= minExperience
     */
    @Query("SELECT a FROM Astronaut a WHERE a.experienceYears >= :minExperience")
    List<Astronaut> findByMinimumExperience(@Param("minExperience") int minExperience);

    /**
     * Find astronauts by last name (case-insensitive partial match)
     *
     * @param lastName Last name to search for
     * @return List of matching astronauts
     */
    List<Astronaut> findByLastNameContainingIgnoreCase(String lastName);
}
