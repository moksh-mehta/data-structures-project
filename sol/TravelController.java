package sol;

import src.ITravelController;
import src.TransportType;
import src.TravelCSVParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Implementation for TravelController
 */
public class TravelController implements ITravelController<City, Transport> {

    // Why is this field of type TravelGraph and not IGraph?
    // Are there any advantages to declaring a field as a specific type rather than the interface?
    // If this were of type IGraph, could you access methods in TravelGraph not declared in IGraph?
    // Hint: perhaps you need to define a method!
    private TravelGraph graph;

    /**
     * Constructor for TravelController
     */
    public TravelController() {
    }

    /**
     * A method that loads the CSV
     * @param citiesFile    the filename of the cities csv
     * @param transportFile the filename of the transportations csv
     * @return Success/failure messages as strings
     */
    @Override
    public String load(String citiesFile, String transportFile) {
        this.graph = new TravelGraph();
        TravelCSVParser parser = new TravelCSVParser();

        Function<Map<String, String>, Void> addVertex = map -> {
            this.graph.addVertex(new City(map.get("name")));
            return null; // need explicit return null to account for Void type
        };

        Function<Map<String, String>, Void> addConnections = map -> {
            this.buildEdge(map.get("origin"), map.get("destination"),
                    map.get("type"), map.get("price"), map.get("duration"));
            return null;
        };

        try {
            // pass in string for CSV and function to create City (vertex) using city name
            parser.parseLocations(citiesFile, addVertex);
        } catch (IOException e) {
            return "Error parsing file: " + citiesFile;
        }


        // hint: note that parseLocations and parseTransportation can
        //       throw IOExceptions. How can you handle these calls cleanly?
        try {
            parser.parseTransportation(transportFile, addConnections);
        } catch (IOException e) {
            return "Error parsing file: " + transportFile;
        }
        return "Successfully loaded cities and transportation files.";
    }

    /**
     * Method that builds an edge from string values
     * @param origin origin location
     * @param destination destination location
     * @param type the type of transportation
     * @param price the price of transportation
     * @param duration the duration of transportation
     */
    public void buildEdge(String origin, String destination, String type,
                          String price, String duration) {
        this.graph.getCityByName(origin)
                .addOut(new Transport(this.graph.getCityByName(origin),
                        this.graph.getCityByName(destination),
                        TransportType.fromString(type),
                        Double.parseDouble(price),
                        Double.parseDouble(duration)));
    }

    /**
     * Method that finds the fastest route from a source to a destination using Dijkstra's
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return The list of transports in the fastest route
     */
    @Override
    public List<Transport> fastestRoute(String source, String destination) {
        Function<Transport, Double> transportTime =
            transport -> {
                return transport.getMinutes();
            };
        Dijkstra dijkstra = new Dijkstra();
        return dijkstra.getShortestPath(this.graph,
                this.graph.getCityByName(source),
                this.graph.getCityByName(destination),
                transportTime);
    }

    /**
     * Method that finds the cheapest route from a source to a destination using Dijkstra's
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return The list of transports in the fastest route
     */
    @Override
    public List<Transport> cheapestRoute(String source, String destination) {
        Function<Transport, Double> transportCost =
                transport -> {
                    return transport.getPrice();
                };
        Dijkstra dijkstra = new Dijkstra();
        return dijkstra.getShortestPath(this.graph,
                this.graph.getCityByName(source),
                this.graph.getCityByName(destination),
                transportCost);
    }

    /**
     * Method that finds the most direct route from a source to a destination using BFS
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return The list of transports in the most direct route
     */
    @Override
    public List<Transport> mostDirectRoute(String source, String destination) {
        BFS bfs = new BFS();
        return bfs.getPath(this.graph, this.graph.getCityByName(source),
                this.graph.getCityByName(destination));
    }

    /**
     * Returns the graph stored by the controller
     *
     * NOTE: You __should not__ be using this in your implementation, this is purely meant to be used for the visualizer
     *
     * @return The TravelGraph currently stored in the TravelController
     */
    public TravelGraph getGraph() {
        return this.graph;
    }
}
