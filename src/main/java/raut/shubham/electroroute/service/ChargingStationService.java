package raut.shubham.electroroute.service;


import raut.shubham.electroroute.entity.ChargingStation;

import java.util.List;

public interface ChargingStationService {


    Integer postStation(ChargingStation chargingStation);

    List<ChargingStation> getStationList();
}