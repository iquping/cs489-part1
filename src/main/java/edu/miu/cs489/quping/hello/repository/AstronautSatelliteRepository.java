package edu.miu.cs489.quping.hello.repository;

import edu.miu.cs489.quping.hello.entity.AstronautSatellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@Repository
public interface AstronautSatelliteRepository extends JpaRepository<AstronautSatellite, Long> {

    /**
     * Find assignments by astronaut ID
     *
     * @param astronautId Astronaut ID
     * @return List of assignments for the astronaut
     */
    List<AstronautSatellite> findByAstronautId(Long astronautId);

    /**
     * Find assignments by satellite ID
     *
     * @param satelliteId Satellite ID
     * @return List of assignments for the satellite
     */
    List<AstronautSatellite> findBySatelliteId(Long satelliteId);

    /**
     * Find assignment by astronaut ID and satellite ID
     *
     * @param astronautId Astronaut ID
     * @param satelliteId Satellite ID
     * @return Optional containing assignment if found
     */
    Optional<AstronautSatellite> findByAstronautIdAndSatelliteId(Long astronautId, Long satelliteId);

    /**
     * Delete assignment by astronaut ID and satellite ID
     *
     * @param astronautId Astronaut ID
     * @param satelliteId Satellite ID
     */
    void deleteByAstronautIdAndSatelliteId(Long astronautId, Long satelliteId);
}
