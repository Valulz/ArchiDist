package tp.soap;

import tp.model.City;
import tp.model.CityManagerService;
import tp.model.CityNotFound;
import tp.model.Position;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class MyClient {
    private static final QName SERVICE_NAME = new QName("http://model.tp/", "CityManagerService");
    private static final QName PORT_NAME = new QName("http://model.tp/", "CityManagerPort");

    public static void main(String[] args) throws MalformedURLException, CityNotFound {
        URL wsdlURL = new URL("http://127.0.0.1:8084/citymanager?wsdl");
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        CityManagerService cityManager = service.getPort(PORT_NAME, CityManagerService.class);

        System.out.println(cityManager.getCities());
        City c = new City("Zanarkand", 16, 64, "Hyrule");
        cityManager.addCity(c);
        System.out.println(cityManager.getCities());
        System.out.println(cityManager.searchExactPosition(new Position(16, 64)));
        cityManager.addCity(new City("Zan", 15.9, 64, "Hy"));
        System.out.println(cityManager.searchFor("Zan"));
        System.out.println(cityManager.searchNear(new Position(15.95, 64)));
        cityManager.removeCity(c);
        System.out.println(cityManager.getCities());
    }

}
