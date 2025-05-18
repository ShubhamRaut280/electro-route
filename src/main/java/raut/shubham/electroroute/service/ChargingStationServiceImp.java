package raut.shubham.electroroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raut.shubham.electroroute.entity.ChargingStation;
import raut.shubham.electroroute.repository.ChargingStationRepository;

import java.util.List;

@Service
public class ChargingStationServiceImp implements ChargingStationService{

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Override
    public Integer postStation(ChargingStation chargingStation) {
        return chargingStationRepository.save(chargingStation).getStationId();
    }

    @Override
    public List<ChargingStation> getStationList() {
        return chargingStationRepository.findAll();
    }
}