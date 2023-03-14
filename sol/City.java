package sol;

import src.IEdge;
import src.IVertex;

import java.util.HashSet;
import java.util.Set;

/**
 * A City class representing the vertex of a travel graph
 */
public class City implements IVertex<Transport> {

    /**
     * Constructor for a City
     * @param name The name of the city
     */
    public City(String name) {
        // TODO: implement this method
    }

    // TODO: implement this method
    @Override
    public Set<Transport> getOutgoing() {
        return null;
    }

    // TODO: implement this method
    @Override
    public void addOut(Transport outEdge) {

    }

    @Override
    public String toString() {
        return "fixme"; // TODO
    }
}
