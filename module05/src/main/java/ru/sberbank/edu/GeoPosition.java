package ru.sberbank.edu;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(int degreeLat,int minutesLat, int secondsLat,int degreeLong,int minutesLong,int secondsLong) {
        this.latitude = degreeLat+ (double) minutesLat/60+ (double) secondsLat /3600;
        this.longitude = degreeLong+  (double) minutesLong/60+ (double) secondsLong /3600;
        this.latitude = Math.toRadians(this.latitude);
        this.longitude = Math.toRadians(this.longitude);
    }

    // getters and toString


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}