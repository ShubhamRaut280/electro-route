package raut.shubham.electroroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raut.shubham.electroroute.entity.ChargingStation;
import raut.shubham.electroroute.repository.ChargingStationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChargingStationServiceImp implements ChargingStationService{

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Override
    public String postStation(ChargingStation chargingStation) {
        return chargingStationRepository.save(chargingStation).getStationId().toString();
    }

    @Override
    public List<ChargingStation> getStationList() {
        return chargingStationRepository.findAll();
    }

    @Override
    public Optional<ChargingStation> getStationById(Integer id) {
        return chargingStationRepository.findById(id);
    }


}