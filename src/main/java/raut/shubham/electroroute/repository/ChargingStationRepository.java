package raut.shubham.electroroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import raut.shubham.electroroute.entity.ChargingStation;

import java.util.List;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation,Integer> {

    @Query(value = """
        SELECT * FROM charging_station
        WHERE lat BETWEEN :minLat AND :maxLat
          AND lng BETWEEN :minLng AND :maxLng
          AND (6371 * acos(
                cos(radians(:lat)) * cos(radians(lat)) * cos(radians(lng) - radians(:lng)) +
                sin(radians(:lat)) * sin(radians(lat))
          )) <= :radius
        ORDER BY lat, lng
        """, nativeQuery = true)
    List<ChargingStation> getNearbyChargingStations(
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("minLat") double minLat,
            @Param("maxLat") double maxLat,
            @Param("minLng") double minLng,
            @Param("maxLng") double maxLng,
            @Param("radius") double radius
    );}