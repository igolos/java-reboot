package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Travel Service.
 */
public class TravelService {


    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        boolean cityExists = cities.stream()
                .anyMatch(existingCity -> existingCity.getName().equals(cityInfo.getName()));
        if (cityExists) {
            throw new IllegalArgumentException("City already exists: " + cityInfo.getName());
        } else
            cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        boolean cityExists = cities.stream()
                .anyMatch(city -> city.getName().equals(cityName));

        if (!cityExists) {
            throw new IllegalArgumentException("City not found: " + cityName);
        }

        cities.removeIf(city -> city.getName().equals(cityName));
    }


    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream()
                .map(CityInfo::getName)
                .collect(Collectors.toList());
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        boolean srcCityNameInList = cities.stream()
                .anyMatch(city -> city.getName().equals(srcCityName));
        boolean destCityNameInList=cities.stream()
                .anyMatch(city -> city.getName().equals(destCityName));
        if(!srcCityNameInList && !destCityNameInList) {
            throw new IllegalArgumentException("Cities don't exist");
        } else
        if(!srcCityNameInList || !destCityNameInList) {
            throw new IllegalArgumentException("Source or destination city doesn't exist");
        }
        else{
            GeoPosition srcPosition = cities.stream()
                    .filter(city -> city.getName().equals(srcCityName))
                    .findAny()
                    .map(CityInfo::getPosition)
                    .orElseThrow();

            GeoPosition destPosition = cities.stream()
                    .filter(city -> city.getName().equals(destCityName))
                    .findAny()
                    .map(CityInfo::getPosition)
                    .orElseThrow();

            // косинусы и синусы широт и разницы долгот
            double cl1 = Math.cos(srcPosition.getLatitude());
            double cl2 = Math.cos(destPosition.getLatitude());
            double sl1 = Math.sin(srcPosition.getLatitude());
            double sl2 = Math.sin(destPosition.getLatitude());
            double delta = destPosition.getLongitude() - srcPosition.getLongitude();
            double cdelta = Math.cos(delta);
            double sdelta = Math.sin(delta);

            // вычисления длины большого круга
            double y = Math.sqrt(Math.pow(cl2 * sdelta, 2) + Math.pow(cl1 * sl2 - sl1 * cl2 * cdelta, 2));
            double x = sl1 * sl2 + cl1 * cl2 * cdelta;

            // вычисление угла
            int earth=6372795;
            double ad = Math.atan2(y, x);
            double dist =  ad * earth/1000;
            return (int) dist;
        }
        }



    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
            public List<String> getCitiesNear(String cityName, int radius) {
             return null;
    }
}
