package raut.shubham.electroroute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Area {
    double maxLat ;
    double minLat ;
    double maxLng ;
    double minLng ;
}
