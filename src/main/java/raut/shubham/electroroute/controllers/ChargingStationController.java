package raut.shubham.electroroute.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import raut.shubham.electroroute.models.ChargingStation;
import raut.shubham.electroroute.services.ChargingStationService;

import java.util.List;

@RestController
@RequestMapping("/chargingStation")
public class ChargingStationController {

    @Autowired
    private ChargingStationService chargingStationService;

    @PostMapping("/post")
    public Integer postStation(@RequestBody ChargingStation chargingStation) {
        return chargingStationService.postStation(chargingStation);
    }

    @GetMapping("/allList")
    public List<ChargingStation> getStationList(){
        return chargingStationService.getStationList();
    }
}