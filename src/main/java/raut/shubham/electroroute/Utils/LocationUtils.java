package raut.shubham.electroroute.Utils;

import raut.shubham.electroroute.entity.Area;

public class LocationUtils {


    public static Area getArea(double radius, double lat, double lng){
        double earthRadius = 6371; // km
        double maxLat = lat + Math.toDegrees(radius / earthRadius);
        double minLat = lat - Math.toDegrees(radius / earthRadius);
        double maxLng = lng + Math.toDegrees(radius / earthRadius / Math.cos(Math.toRadians(lat)));
        double minLng = lng - Math.toDegrees(radius / earthRadius / Math.cos(Math.toRadians(lat)));
        return new Area(maxLat, minLat, maxLng, minLng);
    }


}
