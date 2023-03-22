package sol;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import src.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    @Test
    public void testTravelGraph(){
        TravelGraph t1 = new TravelGraph();
        t1.addVertex(new City("boston"));
        City Boston = t1.getCityByName("boston");
        t1.addVertex(new City("providence"));
        City Providence = t1.getCityByName("providence");
        t1.addEdge(Boston, new Transport(Boston, Providence,
                TransportType.BUS, 15, 120));

        System.out.println(this.controller.mostDirectRoute("Providence",
                "Boston"));

        System.out.println(this.controller.cheapestRoute("Boston",
                "Providence"));


    }
}
