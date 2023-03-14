package sol;

import src.IEdge;
import src.ITransport;
import src.IVertex;
import src.TransportType;

/**
 * A Transport class representing the edge of a travel graph
 */
public class Transport implements IEdge<City>, ITransport {

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
        // TODO: implement this method
    }

    // TODO: implement this method
    @Override
    public City getSource() {
        return null;
    }

    // TODO: implement this method
    @Override
    public City getTarget() {
        return null;
    }

    // TODO: implement this method
    @Override
    public double getPrice() {
        return 0;
    }

    // TODO: implement this method
    @Override
    public double getMinutes() {
        return 0;
    }

    // TODO: implement this method
    @Override
    public String getType() {
        return null;
    }

    @Override
    public String toString() {
        return this.getSource().toString() + " -> " + this.getTarget().toString() +
                ", Type: " + this.getType() +
                ", Cost: $" + this.getPrice() +
                ", Duration: " + this.getMinutes() + " minutes";
    }
}
