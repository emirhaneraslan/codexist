package com.mapproject.codexist.DataAccess;

import com.mapproject.codexist.Entities.MapInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapRepository extends JpaRepository<MapInfo,Long> {

    Optional<MapInfo> findByLongitudeAndLatitudeAndRadius(Double longitude, Double latitude, Integer radius);


}
