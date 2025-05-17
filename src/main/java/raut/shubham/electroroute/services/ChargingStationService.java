package raut.shubham.electroroute.services;


import raut.shubham.electroroute.models.ChargingStation;

import java.util.List;

public interface ChargingStationService {


    Integer postStation(ChargingStation chargingStation);

    List<ChargingStation> getStationList();
}