package tp.model;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represent a city manager, it can
 * <ul>
 * <li>add a city</li>
 * <li>remove a city</li>
 * <li>return the list of cities</li>
 * <li>search a city with a given name</li>
 * <li>search a city at a position</li>
 * <li>return the list of cities near 10km of the given position</li>
 * </ul>
 */
@WebService(endpointInterface = "tp.model.CityManagerService", serviceName = "CityManagerService")
public class CityManager implements CityManagerService {


    /**
     * Constant used in the method searchNear().
     * The number of kilometers around which we are looking for city.
     */
    private static final int NEAR_KM = 10;

    /**
     * Constant used in the method searchNear().
     * Represents the Earth radius.
     */
    private static final double EARTH = 6374892.5;

    private List<City> cities;

    /**
     * Constructor of CityManager
     */
    public CityManager() {
        this.cities = new LinkedList<City>();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Appends the specified city to the end of the list
     *
     * @param city the city to add at the end of the list
     * @return true if the city has been added
     */
    public boolean addCity(City city) {
        return cities.add(city);
    }

    /**
     * Remove the first occurrence of the specified city from this list, if it is presents.
     *
     * @param city the city to be removed.
     * @return true if the city has been removed.
     */
    public boolean removeCity(City city) {
        return cities.remove(city);
    }

    /**
     * Remove all the cities from the list.
     */
    public void removeCities(){
        cities.clear();
    }

    /**
     * Search and return the cities that match the specified name
     *
     * @param cityName the name of the city/cities searches
     * @return The list of the cities found
     */
    public List<City> searchFor(String cityName) {
        List<City> citiesSearched = new ArrayList<City>();

        for (City c : cities) {
            if (c.getName().contains(cityName)) {
                citiesSearched.add(c);
            }
        }

        return citiesSearched;
    }

    /**
     * Search for the city at the position specified.
     *
     * @param position position of the searched city
     * @return the city found
     * @throws CityNotFound if no city is found at the specified position
     */
    public City searchExactPosition(Position position) throws CityNotFound {
        for (City city : cities) {
            if (position.equals(city.getPosition())) {
                return city;
            }
        }
        throw new CityNotFound();
    }

    /**
     * Search cities around 10 kilometers from the specified position
     *
     * @param p the position searched
     * @return the list of city found
     */
    public List<City> searchNear(Position p) {

		/*pLat and pLon are respectively the latitude and longitude of p convert in radiant*/
        double pLat = Math.toRadians(p.getLatitude()), pLon = Math.toRadians(p.getLongitude());

        double oLat, oLon;

        double tmp;

        List<City> citiesFound = new ArrayList<City>();

        for (City c : cities) {
            oLat = Math.toRadians(c.getPosition().getLatitude());
            oLon = Math.toRadians(c.getPosition().getLongitude());

            tmp = Math.acos(
                    Math.sin(pLat)
                            * Math.sin(oLat)
                            + Math.cos(pLat)
                            * Math.cos(oLat)
                            * Math.cos(oLon - pLon)
            );

			/*this instruction convert the distance into meters (by multiply it with the earth radius)
            * then by dividing it by 1000 into kilometers*/
            tmp = tmp * EARTH / 1000;

            if (tmp <= NEAR_KM) {
                citiesFound.add(c);
            }
        }

        return citiesFound;
    }
}
