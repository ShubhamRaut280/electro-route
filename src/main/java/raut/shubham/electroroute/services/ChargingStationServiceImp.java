package raut.shubham.electroroute.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import raut.shubham.electroroute.models.ChargingStation;
import raut.shubham.electroroute.repositories.ChargingStationRepository;

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