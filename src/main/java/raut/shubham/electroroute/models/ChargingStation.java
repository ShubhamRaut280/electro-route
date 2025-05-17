package raut.shubham.electroroute.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "ChargingStation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargingStation {

    @Id
    private Integer stationId;

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