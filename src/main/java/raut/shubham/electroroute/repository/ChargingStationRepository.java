package raut.shubham.electroroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import raut.shubham.electroroute.entity.ChargingStation;

@Repository
public interface ChargingStationRepository extends MongoRepository<ChargingStation,Integer> {

}