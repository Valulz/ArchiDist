package tp.model;

/**
 * Thrown to indicate that a city has not been found
 */
public class CityNotFound extends Exception {

    /**
     * Constructor of CityNotFound
     */
    public CityNotFound() {
        super("[Error] City Not Found");
    }

    /**
     * Return a XMLMessage that represents the exception
     *
     * @return an xml exception
     */
    public XMLMessage getXMLException() {
        return new XMLMessage(this.getMessage());
    }
}
