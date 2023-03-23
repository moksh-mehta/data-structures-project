package sol;

import src.IGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation for TravelGraph
 */
public class TravelGraph implements IGraph<City, Transport> {

    private HashSet<City> vertices; //You will need a data structure to
    // store
    // all of these vertices
    private HashMap<String, City> test1;

    /**
     * Constructor for the TravelGraph
     */
    public TravelGraph() {
        this.vertices = new HashSet<City>();
        this.test1 = new HashMap<String, City>();
    }

    /**
     * Method that adds a vertex to a TravelGraph
     * @param vertex the vertex
     */
    @Override
    public void addVertex(City vertex) {
        // TODO: implement this method!
        this.vertices.add(vertex);
        this.test1.put(vertex.toString(), vertex);
    }

    /**
     * Method that adds an edge to a TravelGraph
     * @param origin the origin of the edge.
     * @param edge the edge object
     */
    @Override
    public void addEdge(City origin, Transport edge) {
        // TODO: implement this method!
        this.test1.get(origin.toString()).addOut(edge);
    }

    /**
     * Getter method to get the vertices field
     * @return vertices field
     */
    @Override
    public Set<City> getVertices() {
        // TODO: implement this method!
        return this.vertices;
    }

    /**
     * Returns the source city of an edge
     * @param edge the edge
     * @return source city
     */
    @Override
    public City getEdgeSource(Transport edge) {
        // TODO: implement this method!
        return edge.getSource();
    }

    /**
     * Returns the target of the edge, the direction the arrow is pointing
     * @param edge the edge
     * @return city to which edge points
     */
    @Override
    public City getEdgeTarget(Transport edge) {
        // TODO: implement this method!
        return edge.getTarget();
    }

    /**
     * Gets all the outgoing edges from a city
     * @param fromVertex the vertex
     * @return list of transports
     */
    @Override
    public Set<Transport> getOutgoingEdges(City fromVertex) {
        // TODO: implement this method!
        return fromVertex.getOutgoing();
    }

    // TODO: feel free to add your own methods here!
    // hint: maybe you need to get a City by its name

    /**
     * Method that gets a city by its name
     * @param cityName
     * @return the city
     */
    public City getCityByName(String cityName) {
        return this.test1.get(cityName);
    }
}