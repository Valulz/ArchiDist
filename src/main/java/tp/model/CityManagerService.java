package tp.model;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface CityManagerService {
    List<City> getCities();
    boolean addCity(City city);
    boolean removeCity(City city);
    void removeCities();
    List<City> searchFor(String cityName);
    City searchExactPosition(Position position) throws CityNotFound;
    List<City> searchNear(Position p);

}
