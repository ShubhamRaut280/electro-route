package raut.shubham.electroroute.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raut.shubham.electroroute.entity.ChargingStation;
import raut.shubham.electroroute.service.ChargingStationService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Charging Stations", description = "CRUD operations and nearby station discovery")
@RestController
@RequestMapping("/api/stations")
@SecurityRequirement(name = "bearerAuth") // Assumes JWT security
public class ChargingStationController {

    @Autowired
    private ChargingStationService chargingStationService;

    @Operation(summary = "Add a new charging station")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Charging station added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public String postStation(@RequestBody ChargingStation chargingStation) {
        return chargingStationService.postStation(chargingStation);
    }

    @Operation(summary = "Get all charging stations")
    @ApiResponse(responseCode = "200", description = "List of all charging stations")
    @GetMapping("/all")
    public List<ChargingStation> getStationList() {
        return chargingStationService.getStationList();
    }

    @Operation(summary = "Get charging station by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Charging station found"),
            @ApiResponse(responseCode = "404", description = "Charging station not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        Optional<ChargingStation> stationOptional = chargingStationService.getStationById(id);
        if (stationOptional.isEmpty()) {
            return new ResponseEntity<>("Charging Station Not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(stationOptional.get(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Get nearby charging stations by location and radius")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of nearby charging stations")
    })
    @GetMapping("/nearby")
    public ResponseEntity<List<ChargingStation>> getNearbyStations(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "2") double radiusKm
    ) {
        return new ResponseEntity<>(chargingStationService.getNearbyStations(lat, lng, radiusKm), HttpStatus.OK);
    }
}
