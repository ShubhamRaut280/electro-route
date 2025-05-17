package raut.shubham.electroroute.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import raut.shubham.electroroute.models.ChargingStation;

@Repository
public interface ChargingStationRepository extends MongoRepository<ChargingStation,Integer> {

}