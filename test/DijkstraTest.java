package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sol.*;
import src.IDijkstra;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;

import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Your Dijkstra's tests should all go in this class!
 * The test we've given you will pass if you've implemented Dijkstra's correctly, but we still
 * expect you to write more tests using the City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 */
public class DijkstraTest {

    private static final double DELTA = 0.001;

    private SimpleGraph graph;
    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;
    private SimpleVertex d;
    private SimpleVertex e;
    private TravelGraph graph1;
    private TravelGraph graph2;
    private TravelGraph graph3;
    private Function <Transport, Double> transportPriceCalculation;
    private Function <Transport, Double> transportTimeCalculation;

    @Before
    public void getAllGraphs(){
        TravelController t1 = new TravelController();
        t1.load("data/cities1.csv", "data/transport1.csv");
        this.graph1 = t1.getGraph();

        TravelController t2 = new TravelController();
        t2.load("data/cities2.csv", "data/transport2.csv");
        this.graph2 = t2.getGraph();

        TravelController t3 = new TravelController();
        t3.load("data/cities3.csv", "data/transport3.csv");
        this.graph3 = t3.getGraph();

        this.transportPriceCalculation = e -> e.getPrice();
        this.transportTimeCalculation = e -> e.getMinutes();

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

        this.a = new SimpleVertex("a");
        this.b = new SimpleVertex("b");
        this.c = new SimpleVertex("c");
        this.d = new SimpleVertex("d");
        this.e = new SimpleVertex("e");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);
        this.graph.addVertex(this.d);
        this.graph.addVertex(this.e);

        this.graph.addEdge(this.a, new SimpleEdge(100, this.a, this.b));
        this.graph.addEdge(this.a, new SimpleEdge(3, this.a, this.c));
        this.graph.addEdge(this.a, new SimpleEdge(1, this.a, this.e));
        this.graph.addEdge(this.c, new SimpleEdge(6, this.c, this.b));
        this.graph.addEdge(this.c, new SimpleEdge(2, this.c, this.d));
        this.graph.addEdge(this.d, new SimpleEdge(1, this.d, this.b));
        this.graph.addEdge(this.d, new SimpleEdge(5, this.e, this.d));
    }

    /**
     * A sample test that tests Dijkstra's on a simple graph.
     * Checks that the shortest path using Dijkstra's is what we
     * expect.
     */
    @Test
    public void testSimple() {
        this.createSimpleGraph();

        IDijkstra<SimpleVertex, SimpleEdge> dijkstra = new Dijkstra<>();
        Function<SimpleEdge, Double> edgeWeightCalculation = e -> e.weight;
        // a -> c -> d -> b
        List<SimpleEdge> path =
            dijkstra.getShortestPath(this.graph, this.a, this.b, edgeWeightCalculation);
        assertEquals(6, SimpleGraph.getTotalEdgeWeight(path), DELTA);
        assertEquals(3, path.size());

        // c -> d -> b
        path = dijkstra.getShortestPath(this.graph, this.c, this.b, edgeWeightCalculation);
        assertEquals(3, SimpleGraph.getTotalEdgeWeight(path), DELTA);
        assertEquals(2, path.size());
    }

    @Test
    public void testGraph1() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();

        List<Transport> path =
                dijkstra.getShortestPath(this.graph1,
                        this.graph1.getCityByName("Boston"),
                        this.graph1.getCityByName("Providence"),
                        this.transportPriceCalculation);
        assertEquals(7, path.get(0).getPrice(), DELTA);
        assertEquals(1, path.size());

        List<Transport> path2 = dijkstra.getShortestPath(this.graph1,
                this.graph1.getCityByName("Providence"),
                this.graph1.getCityByName("New York City"),
                this.transportPriceCalculation);
        Assert.assertEquals(274, path2.get(0).getPrice()+
                        path2.get(1).getPrice(),
                DELTA);
        Assert.assertEquals(267, path2.get(1).getPrice(), DELTA);
        Assert.assertEquals(7, path2.get(0).getPrice(), DELTA);
        Assert.assertEquals(2, path2.size());


        List<Transport> path3 = dijkstra.getShortestPath(this.graph1,
                this.graph1.getCityByName("Providence"),
                this.graph1.getCityByName("New York City"),
                this.transportTimeCalculation);
        Assert.assertEquals(80, path3.get(0).getMinutes(), DELTA);
        Assert.assertEquals(50, path3.get(1).getMinutes(), DELTA);
        Assert.assertEquals(2, path3.size(), DELTA);
    }

    @Test
    public void testGraph2() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();

        List<Transport> path4 = dijkstra.getShortestPath(this.graph2,
                this.graph2.getCityByName("Boston"),
                this.graph2.getCityByName("Providence"),
                this.transportPriceCalculation);
        Assert.assertEquals(3, path4.get(0).getPrice(), DELTA);
        Assert.assertEquals(2, path4.get(1).getPrice(), DELTA);
        Assert.assertEquals(1, path4.get(2).getPrice(), DELTA);
        Assert.assertEquals(1, path4.get(0).getMinutes(), DELTA);

        List<Transport> path5 = dijkstra.getShortestPath(this.graph2,
                this.graph2.getCityByName("Washington"),
                this.graph2.getCityByName("Chicago"),
                this.transportTimeCalculation);
        Assert.assertEquals(1, path5.get(0).getMinutes(), DELTA);
    }

    @Test
    public void testGraph3(){
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();

        List<Transport> path6 = dijkstra.getShortestPath(this.graph3,
                this.graph3.getCityByName("Chennai"),
                this.graph3.getCityByName("Bangalore"),
                this.transportPriceCalculation);
        Assert.assertEquals(30, path6.get(0).getMinutes(), DELTA);

        List<Transport> path7 = dijkstra.getShortestPath(this.graph3,
                this.graph3.getCityByName("Bangalore"),
                this.graph3.getCityByName("Hyderabad"),
                this.transportPriceCalculation);
        Assert.assertEquals(5, path7.get(0).getPrice(), DELTA);
        Assert.assertEquals(100, path7.get(1).getPrice(), DELTA);
        Assert.assertEquals(2, path7.size(), DELTA);

        List<Transport> path8 = dijkstra.getShortestPath(this.graph3,
                this.graph3.getCityByName("Bangalore"),
                this.graph3.getCityByName("Hyderabad"),
                this.transportTimeCalculation);
        Assert.assertEquals(60, path8.get(0).getMinutes(), DELTA);
    }

}
