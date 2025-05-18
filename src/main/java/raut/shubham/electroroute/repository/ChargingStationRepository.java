package raut.shubham.electroroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raut.shubham.electroroute.entity.ChargingStation;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation,Integer> {

}