package raut.shubham.electroroute.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raut.shubham.electroroute.entity.ChargingStation;
import raut.shubham.electroroute.service.ChargingStationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stations")
public class ChargingStationController {

    @Autowired
    private ChargingStationService chargingStationService;

    @PostMapping("/add")
    public String postStation(@RequestBody ChargingStation chargingStation) {
        return chargingStationService.postStation(chargingStation);
    }

    @GetMapping("/all")
    public List<ChargingStation> getStationList(){
        return chargingStationService.getStationList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id){
        Optional<ChargingStation> stationOptional = chargingStationService.getStationById(id);
        if(stationOptional.isEmpty()){
            return new ResponseEntity<>("Charging Station Not found", HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(stationOptional.get(), HttpStatus.OK);
        }
    }

}