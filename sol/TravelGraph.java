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

    public TravelGraph(){
        this.vertices = new HashSet<City>();
        this.test1 = new HashMap<String, City>();
    }

    @Override
    public void addVertex(City vertex) {
        // TODO: implement this method!
        this.vertices.add(vertex);
        this.test1.put(vertex.toString(), vertex);
    }

    @Override
    public void addEdge(City origin, Transport edge) {
        // TODO: implement this method!
        this.test1.get(origin.toString()).addOut(edge);
    }

    @Override
    public Set<City> getVertices() {
        // TODO: implement this method!
        return this.vertices;
    }

    @Override
    public City getEdgeSource(Transport edge) {
        // TODO: implement this method!
        return edge.getSource();
    }

    @Override
    public City getEdgeTarget(Transport edge) {
        // TODO: implement this method!
        return edge.getTarget();
    }

    @Override
    public Set<Transport> getOutgoingEdges(City fromVertex) {
        // TODO: implement this method!
        return fromVertex.getOutgoing();
    }

    // TODO: feel free to add your own methods here!
    // hint: maybe you need to get a City by its name
    public City getCityByName(String cityName) {
        return this.test1.get(cityName);
    }
}