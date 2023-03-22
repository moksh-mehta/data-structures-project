package sol;

import src.IDijkstra;
import src.IEdge;
import src.IGraph;
import src.IVertex;

import java.util.*;
import java.util.function.Function;

import static java.lang.Double.MAX_VALUE;

/**
 * Implementation for Dijkstra's algorithm
 *
 * @param <V> the type of the vertices
 * @param <E> the type of the edges
 */
public class Dijkstra<V extends IVertex<E>, E extends IEdge<V>> implements IDijkstra<V, E> {

    // TODO: implement the getShortestPath method!
    @Override
    public List<E> getShortestPath(IGraph<V, E> graph, V source, V destination,
                                   Function<E, Double> edgeWeight) {
        // when you get to using a PriorityQueue, remember to remove and re-add a vertex to the
        // PriorityQueue when its priority changes!
        HashMap<V, Double> routeDist = new HashMap<>();
        Comparator<V> cityQueue = (city1, city2) -> {
            return Double.compare(routeDist.get(city1),
                    routeDist.get(city2));
        };


        PriorityQueue toCheckQueue = new PriorityQueue<V>(cityQueue);
        HashMap<V, E> cameFrom = new HashMap<>();

        for (V city: graph.getVertices()) {
            routeDist.put(city, MAX_VALUE);
            toCheckQueue.add(city);
        }
        routeDist.put(source, 0.0);

        while (!toCheckQueue.isEmpty()) {
            V toCheck = (V) toCheckQueue.poll();
            for (E transport: toCheck.getOutgoing()) {
                V neighbour = transport.getTarget();
                if (routeDist.get(toCheck) + edgeWeight.apply(transport) < routeDist.get(neighbour)) {
                    routeDist.put(neighbour,
                            routeDist.get(toCheck) + edgeWeight.apply(transport));
                    cameFrom.put(neighbour, transport);
                    toCheckQueue.add(neighbour);
                }
            }
            System.out.println(routeDist);
        }
        if (routeDist.get(destination) == MAX_VALUE) {
            return new LinkedList<E>();
        }
        return this.backtracking(source, destination, cameFrom);
    }

    public List<E> backtracking(V source, V destination,
                                HashMap<V,E> cameFrom) {
        LinkedList<E> path = new LinkedList<>();
        V currCity = destination;
        while (currCity != source) {
            path.addFirst(cameFrom.get(currCity));
            currCity = cameFrom.get(currCity).getSource();
        }
        return path;
    }

    // TODO: feel free to add your own methods here!
}
