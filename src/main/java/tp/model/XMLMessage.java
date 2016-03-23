package tp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Allow to parse a message through XML.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLMessage {

    private String message;

    /**
     * The constructor with parameters of XMLMessage.
     * Initialize the message of the class.
     *
     * @param message the message to display
     */
    public XMLMessage(String message) {
        this.message = message;
    }

    /**
     * Constructor with no parameters of XMLMessage
     */
    public XMLMessage() {
        this("No Message !");
    }

    public String getMessage() {
        return message;
    }
}
