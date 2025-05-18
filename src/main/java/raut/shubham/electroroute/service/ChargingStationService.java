package raut.shubham.electroroute.service;


import raut.shubham.electroroute.entity.ChargingStation;

import java.util.List;
import java.util.Optional;

public interface ChargingStationService {


    String postStation(ChargingStation chargingStation);

    List<ChargingStation> getStationList();

    Optional<ChargingStation> getStationById(Integer id);
}