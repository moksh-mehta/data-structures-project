package sol;

import src.IEdge;
import src.IVertex;
import java.util.HashSet;
import java.util.Set;

/**
 * A City class representing the vertex of a travel graph
 */
public class City implements IVertex<Transport> {
    private String name;
    private Set<Transport> outgoing;

    /**
     * Constructor for a City
     * @param name The name of the city
     */
    public City(String name) {
        // TODO: implement this method
        this.name = name;
        this.outgoing = new HashSet<Transport>();
    }

    // TODO: implement this method

    /**
     * Gets the outgoing field
     * @return outgoing cities as a set of transports
     */
    @Override
    public Set<Transport> getOutgoing() {
        return this.outgoing;
    }

    // TODO: implement this method

    /**
     * Adds an outgoing edge to the city
     * @param outEdge the outgoing edge
     */
    @Override
    public void addOut(Transport outEdge) {
        this.outgoing.add(outEdge); // Should we add to the end or the beginning? or is this fine?
    }

    /**
     * Gets the name field of the city
     * @return name of the city
     */
    @Override
    public String toString() {
        return this.name;
    }
}
