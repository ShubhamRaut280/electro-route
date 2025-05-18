package raut.shubham.electroroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import raut.shubham.electroroute.entity.ChargingStation;
import raut.shubham.electroroute.service.ChargingStationService;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class ChargingStationController {

    @Autowired
    private ChargingStationService chargingStationService;

    @PostMapping("/add")
    public Integer postStation(@RequestBody ChargingStation chargingStation) {
        return chargingStationService.postStation(chargingStation);
    }

    @GetMapping("/all")
    public List<ChargingStation> getStationList(){
        return chargingStationService.getStationList();
    }
}