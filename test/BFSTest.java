package test;

import org.junit.Assert;
import org.junit.Test;
import sol.*;
import src.TransportType;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Your BFS tests should all go in this class!
 * The test we've given you will pass if you've implemented BFS correctly, but we still expect
 * you to write more tests using the City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 *
 * TODO: Recreate the test below for the City and Transport classes
 * TODO: Expand on your tests, accounting for basic cases and edge cases
 */
public class BFSTest {

    private static final double DELTA = 0.001;

    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;
    private SimpleVertex d;
    private SimpleVertex e;
    private SimpleVertex f;
    private SimpleGraph graph;

    /**
     * Creates a simple graph.
     * You'll find a similar method in each of the Test files.
     * Normally, we'd like to use @Before, but because each test may require a different setup,
     * we manually call the setup method at the top of the test.
     *
     * TODO: create more setup methods!
     */
    public void makeSimpleGraph() {
        this.graph = new SimpleGraph();

        this.a = new SimpleVertex("a");
        this.b = new SimpleVertex("b");
        this.c = new SimpleVertex("c");
        this.d = new SimpleVertex("d");
        this.e = new SimpleVertex("e");
        this.f = new SimpleVertex("f");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);
        this.graph.addVertex(this.d);
        this.graph.addVertex(this.e);
        this.graph.addVertex(this.f);

        this.graph.addEdge(this.a, new SimpleEdge(1, this.a, this.b));
        this.graph.addEdge(this.b, new SimpleEdge(1, this.b, this.c));
        this.graph.addEdge(this.c, new SimpleEdge(1, this.c, this.e));
        this.graph.addEdge(this.d, new SimpleEdge(1, this.d, this.e));
        this.graph.addEdge(this.a, new SimpleEdge(100, this.a, this.f));
        this.graph.addEdge(this.f, new SimpleEdge(100, this.f, this.e));
    }

    /**
     * A sample test that tests BFS on a simple graph. Checks that running BFS gives us the path we expect.
     */
    @Test
    public void testBasicBFS() {
        this.makeSimpleGraph();
        BFS<SimpleVertex, SimpleEdge> bfs = new BFS<>();
        List<SimpleEdge> path = bfs.getPath(this.graph, this.a, this.e);
        assertEquals(SimpleGraph.getTotalEdgeWeight(path), 200.0, DELTA);
        assertEquals(path.size(), 2);
    }

    @Test
    public void testGraph1() {
        TravelController TC1 = new TravelController();
        TC1.load("data/cities1.csv", "data/transport1.csv");
        TravelGraph graph1 = TC1.getGraph();
        List<Transport> path1 = new BFS<City, Transport>().getPath(graph1, graph1.getCityByName("Providence"),
                graph1.getCityByName("New York City"));
        List<Transport> list1 = TC1.mostDirectRoute("Providence","New York City");

        Assert.assertEquals(path1.size(),2);
        Assert.assertEquals(path1.get(1).toString(), new Transport(new City("Boston"),
                new City("New York City"), TransportType.PLANE, 267, 50).toString());
        Assert.assertEquals(path1.get(0).getTarget().toString(), new City("Boston").toString());
        Assert.assertEquals(list1.size(),2);

        List<Transport> path2 = new BFS<City, Transport>().getPath(graph1, graph1.getCityByName("New York City"),
                graph1.getCityByName("Providence"));
        Assert.assertEquals(path2.size(),1);

        List<Transport> path3 = new BFS<City, Transport>().getPath(graph1, graph1.getCityByName("Boston"),
                graph1.getCityByName("Boston"));
        Assert.assertEquals(path3.size(),0);
    }

    @Test
    public void testGraph2() {
        TravelController TC2 = new TravelController();
        TC2.load("data/cities2.csv", "data/transport2.csv");
        TravelGraph graph2 = TC2.getGraph();
        List<Transport> path2 = new BFS<City, Transport>().getPath(graph2, graph2.getCityByName("Providence"),
                graph2.getCityByName("Boston"));

        Assert.assertEquals(path2,new LinkedList<>());

        List<Transport> path3 = new BFS<City, Transport>().getPath(graph2, graph2.getCityByName("Boston"),
                graph2.getCityByName("Providence"));

        Assert.assertEquals(100, path3.get(0).getPrice(), DELTA);
        Assert.assertEquals(1, path3.size());

        List<Transport> path4 = new BFS<City, Transport>().getPath(graph2, graph2.getCityByName("Boston"),
                graph2.getCityByName("Chicago"));
        Assert.assertEquals(2, path4.size());
        Assert.assertEquals(path4.get(1).getTarget().toString(),"Chicago");

    }

    /**
     * Testing the third graph from the files
     */
    @Test
    public void testGraph3() {
        TravelController TC3 = new TravelController();
        TC3.load("data/cities3.csv", "data/transport3.csv");
        TravelGraph graph3 = TC3.getGraph();
        List<Transport> path3 = new BFS<City, Transport>().getPath(graph3, graph3.getCityByName("Hyderabad"),
                graph3.getCityByName("Chennai"));
        List<Transport> list3 = TC3.mostDirectRoute("Hyderabad","Chennai");

        Assert.assertEquals(path3.size(),2);
        Assert.assertEquals(path3.get(1).getSource().toString(), new City("Bangalore").toString());
        Assert.assertEquals(path3.get(0).toString(), new Transport(new City("Hyderabad"),
                new City("Bangalore"),TransportType.BUS, 20, 60).toString());

        List<Transport> path4 = new BFS<City, Transport>().getPath(graph3, graph3.getCityByName("Chennai"),
                graph3.getCityByName("Hyderabad"));
        Assert.assertEquals(path4.size(),1);

        Assert.assertEquals(list3.size(),2);
    }

    // TODO: write more tests + make sure you test all the cases in your testing plan!
}