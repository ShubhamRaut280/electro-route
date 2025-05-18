package raut.shubham.electroroute.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "charging_station")
public class ChargingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long stationId;

    @OneToOne
    @JsonIgnore
    private UserInfo owner;

    private String stationName;

    private String location;

    private String openingTime;

    private String closingTime;

    private String chargingType;

    private String completeAddress;

    private List<String> images;

    private Double latitude;

    private Double longitude;
}