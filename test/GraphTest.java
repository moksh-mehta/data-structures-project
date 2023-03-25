package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sol.City;
import sol.Transport;
import sol.TravelController;
import src.TransportType;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Your Graph method tests should all go in this class!
 * The test we've given you will pass, but we still expect you to write more tests using the
 * City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 *
 */
public class GraphTest {
    private SimpleGraph graph;

    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;

    private SimpleEdge edgeAB;
    private SimpleEdge edgeBC;
    private SimpleEdge edgeCA;
    private SimpleEdge edgeAC;
    private TravelController travelController1;
    private TravelController travelController2;
    private TravelController travelController3;


    /**
     * This method sets up the travel controllers that are used for the
     * rest of the testing file.
     */
    @Before
    public void importGraphs(){
        this.travelController1 = new TravelController();
        this.travelController1.load("data/cities1.csv",
                "data/transport1.csv");

        this.travelController2 = new TravelController();
        this.travelController2.load("data/cities2.csv",
                "data/transport2.csv");

        this.travelController3 = new TravelController();
        this.travelController3.load("data/cities3.csv",
                "data/transport3.csv");
    }

    /**
     * Creates a simple graph.
     * You'll find a similar method in each of the Test files.
     * Normally, we'd like to use @Before, but because each test may require a different setup,
     * we manually call the setup method at the top of the test.
     *
     */
    private void createSimpleGraph() {
        this.graph = new SimpleGraph();

        this.a = new SimpleVertex("A");
        this.b = new SimpleVertex("B");
        this.c = new SimpleVertex("C");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);

        // create and insert edges
        this.edgeAB = new SimpleEdge(1, this.a, this.b);
        this.edgeBC = new SimpleEdge(1, this.b, this.c);
        this.edgeCA = new SimpleEdge(1, this.c, this.a);
        this.edgeAC = new SimpleEdge(1, this.a, this.c);

        this.graph.addEdge(this.a, this.edgeAB);
        this.graph.addEdge(this.b, this.edgeBC);
        this.graph.addEdge(this.c, this.edgeCA);
        this.graph.addEdge(this.a, this.edgeAC);
    }

    /**
     * Sample test for the graph. Tests that the number of vertices and the vertices in the graph are what we expect.
     */
    @Test
    public void testGetVertices() {
        this.createSimpleGraph();

        // test getVertices to check this method AND insertVertex
        assertEquals(this.graph.getVertices().size(), 3);
        assertTrue(this.graph.getVertices().contains(this.a));
        assertTrue(this.graph.getVertices().contains(this.b));
        assertTrue(this.graph.getVertices().contains(this.c));
    }

    /**
     * This method tests the fastest route between cities for the first graph
     */
    @Test
    public void testFastest1() {
        List<Transport> fastestRoute1 = this.travelController1.fastestRoute(
                "New York City",
                "Providence");
        Assert.assertEquals(new Transport(new City("New York City"),
                        new City("Boston"),
                        TransportType.PLANE, 267, 50).toString(),
                fastestRoute1.get(0).toString());

        List<Transport> fastestRoute2 = this.travelController1.fastestRoute(
                "Boston",
                "Providence");
        Assert.assertEquals(new Transport(new City("Boston"),
                        new City("Providence"),
                        TransportType.TRAIN, 13, 80).toString(),
                fastestRoute2.get(0).toString());

        List<Transport> fastestRoute3 = this.travelController1.fastestRoute(
                "Providence",
                "New York City");
        Assert.assertEquals(new Transport(new City("Providence"),
                        new City("Boston"),
                        TransportType.TRAIN, 13, 80).toString(),
                fastestRoute3.get(0).toString());
        Assert.assertEquals(new Transport(new City("Boston"),
                        new City("New York City"),
                        TransportType.PLANE, 257, 50).toString(),
                fastestRoute3.get(1).toString());
        Assert.assertEquals(fastestRoute3.size(), 2);
    }

    /**
     * This method tests the cheapest route between cities for the first
     * graph
     */
    @Test
    public void testCheapest1() {
        List<Transport> graph1cheapest1 = this.travelController1.cheapestRoute(
                "Boston",
                "Providence");

        Assert.assertEquals(new Transport(new City("Boston"),
                        new City("Providence"),
                        TransportType.BUS, 7, 150).toString(),
                graph1cheapest1.get(0).toString());
        Assert.assertEquals(graph1cheapest1.size(),1);

        List<Transport> graph1cheapest2 =
                this.travelController1.cheapestRoute("Providence",
                        "New York City");
        Assert.assertEquals(new Transport(new City("Providence"),
                        new City("Boston"),
                        TransportType.BUS, 7, 150).toString(),
                graph1cheapest2.get(0).toString());
        Assert.assertEquals(new Transport(new City("Boston"),
                        new City("New York City"),
                        TransportType.PLANE, 267, 50).toString(),
                graph1cheapest2.get(1).toString());
        Assert.assertEquals(graph1cheapest2.size(), 2);
    }

    /**
     * This method tests the fastest route between cities for the second graph
     */
    @Test
    public void testFastest2() {
        List<Transport> graph2fastest1 = this.travelController2.fastestRoute(
                "Providence",
                "Boston");
        Assert.assertEquals(graph2fastest1.size(), 0);
        Assert.assertEquals(graph2fastest1.toString(), "[]");

        List<Transport> graph2fastest2 = this.travelController2.fastestRoute(
                "Boston",
                "Chicago");
        Assert.assertEquals(graph2fastest2.size(),2);
    }

    /**
     * This method tests the cheapest route between cities for the second
     * graph
     */
    @Test
    public void testCheapest2() {
        List<Transport> graph2cheapest1 = this.travelController2.cheapestRoute(
                "Boston",
                "Providence");

        Assert.assertEquals(graph2cheapest1.size(), 3);
        Assert.assertEquals(new Transport(new City("Boston"),
                        new City("Durham"),
                        TransportType.TRAIN,3,1).toString(),
                graph2cheapest1.get(0).toString());
        Assert.assertEquals(new Transport(new City("Durham"),
                        new City("Chicago"),
                        TransportType.TRAIN,2,1).toString(),
                graph2cheapest1.get(1).toString());
        Assert.assertEquals(new Transport(new City("Chicago"),
                        new City("Providence"),
                        TransportType.TRAIN,1,1).toString(),
                graph2cheapest1.get(2).toString());
    }

    /**
     * This method tests the fastest route between cities for the third
     * graph
     */
    @Test
    public void testFastest3(){
        List<Transport> graph3fastest1 = this.travelController3.fastestRoute(
                "Chennai",
                "Chennai");
        Assert.assertEquals(graph3fastest1.size(),0);

        List<Transport> graph3fastest2 = this.travelController3.fastestRoute(
                "Chennai",
                "Hyderabad");
        Assert.assertEquals(graph3fastest2.size(),2);
        Assert.assertEquals(new Transport(new City("Chennai"),
                new City("Bangalore"),
                        TransportType.TRAIN, 15, 30).toString(),
                graph3fastest2.get(0).toString());
        Assert.assertEquals(new Transport(new City("Bangalore"),
                new City("Hyderabad"),
                        TransportType.BUS, 20, 60).toString(),
                graph3fastest2.get(1).toString());
    }

    /**
     * This method tests the cheapest route for the third graph
     */
    @Test
    public void testCheapest3(){
        List<Transport> graph3cheapest1 = this.travelController3.cheapestRoute(
                "Chennai",
                "Chennai");
        Assert.assertEquals(graph3cheapest1.size(),0);

        List<Transport> graph3cheapest2 = this.travelController3.cheapestRoute(
                "Bangalore",
                "Hyderabad");
        Assert.assertEquals(new Transport(new City("Bangalore"),
                new City("Hyderabad"),
                        TransportType.BUS,20, 60).toString(),
                graph3cheapest2.get(0).toString());
    }
}
