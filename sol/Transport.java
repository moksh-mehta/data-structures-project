package sol;

import src.IEdge;
import src.ITransport;
import src.IVertex;
import src.TransportType;

/**
 * A Transport class representing the edge of a travel graph
 */
public class Transport implements IEdge<City>, ITransport {

    private City source;
    private City destination;
    private TransportType type;
    private double price;
    private double minutes;

    /**
     * Constructor for Transport
     * @param source  Source city (for this edge)
     * @param destination Destination city (for this edge)
     * @param type Type/method of transport
     * @param price The price
     * @param minutes The time in minutes
     */
    public Transport(City source, City destination, TransportType type, double price,
                     double minutes) {
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.minutes = minutes;
    }


    /**
     * Getter method for the source
     * @return the source field
     */
    @Override
    public City getSource() {
        return this.source;
    }

    /**
     * Getter method for the target
     * @return the target
     */
    @Override
    public City getTarget() {
        return this.destination;
    }


    /**
     * Getter method for the price
     * @return the price
     */
    @Override
    public double getPrice() {
        return this.price;
    }


    /**
     * Getter method for the minutes
     * @return the minutes
     */
    @Override
    public double getMinutes() {
        return this.minutes;
    }

    /**
     * Getter method for the type
     * @return the type
     */
    @Override
    public String getType() {
        return this.type.getLabel();
    }

    /**
     * Converts a Transport to a String
     * @return string representation of a transport
     */
    @Override
    public String toString() {
        return this.getSource().toString() + " -> " + this.getTarget().toString() +
                ", Type: " + this.getType() +
                ", Cost: $" + this.getPrice() +
                ", Duration: " + this.getMinutes() + " minutes";
    }
}
