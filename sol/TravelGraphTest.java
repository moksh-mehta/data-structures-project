package sol;

import org.junit.Before;
import org.junit.Test;
import src.TransportType;
import src.Visualizer;

public class TravelGraphTest {
    private TravelController controller;
    @Before
    public void imports() {
        String citiesFilepath = "data/cities2.csv";
        String transportFilepath = "data/transport2.csv";

        this.controller = new TravelController();
        Visualizer<City, Transport> viz = new Visualizer<>();

        this.controller.load(citiesFilepath, transportFilepath);

        TravelGraph graph = this.controller.getGraph();
    }

    /**
     * Testing the TravelGraph
     */
    @Test
    public void testTravelGraph() {

        TravelGraph cities1 = new TravelGraph();

        City Boston = new City("Boston");
        City Providence = new City("Providence");
        City NYC = new City("New York City");

        cities1.addVertex(Boston);
        cities1.addVertex(Providence);
        cities1.addVertex(NYC);

        // create and insert edges
        Transport Plane1 = new Transport(NYC, Boston, TransportType.PLANE, 267, 50);
        Transport Plane2 = new Transport(Boston, NYC, TransportType.PLANE, 267, 50);
        Transport Bus1 = new Transport(NYC, Providence, TransportType.BUS, 40, 225);
        Transport Bus2 = new Transport(Boston, Providence, TransportType.BUS, 7, 150);
        Transport Bus3 = new Transport(Providence, Boston, TransportType.BUS, 7, 150);
        Transport Train1 = new Transport(Boston, Providence, TransportType.TRAIN, 13, 80);
        Transport Train2 = new Transport(Providence, Boston, TransportType.TRAIN, 13, 80);

        cities1.addEdge(NYC, Plane1);
        cities1.addEdge(Boston, Plane2);
        cities1.addEdge(NYC, Bus1);
        cities1.addEdge(Boston, Bus2);
        cities1.addEdge(Providence, Bus3);
        cities1.addEdge(Boston, Train1);
        cities1.addEdge(Providence, Train2);

        System.out.println(this.controller.cheapestRoute("Boston",
                "Providence"));

    }
}
