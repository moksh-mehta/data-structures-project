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
    @Test
    public void testTravelGraph(){
        TravelGraph t1 = new TravelGraph();
        t1.addVertex(new City("boston"));
        City Boston = t1.getCityByName("boston");
        t1.addVertex(new City("providence"));
        City Providence = t1.getCityByName("providence");
        t1.addEdge(Boston, new Transport(Boston, Providence,
                TransportType.BUS, 15, 120));
        System.out.println(t1.getVertices());
        System.out.println(t1.getOutgoingEdges(Boston));
        System.out.println(t1.getOutgoingEdges(Providence));
        System.out.println(t1.getCityByName("boston").getOutgoing());
    }
}
