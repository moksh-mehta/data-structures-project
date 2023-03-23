package sol;

import src.IBFS;
import src.IEdge;
import src.IGraph;
import src.IVertex;
import java.util.*;

/**
 * Implementation for BFS, implements IBFS interface
 * @param <V> The type of the vertices
 * @param <E> The type of the edges
 */
public class BFS<V extends IVertex<E>, E extends IEdge<V>> implements IBFS<V, E> {

    // TODO: implement the getPath method!

    /**
     * Method that gets the path from a start to end vertex as a list of nodes.
     * @param graph the graph including the vertices
     * @param start the start vertex
     * @param end   the end vertex
     * @return
     */
    @Override
    public List<E> getPath(IGraph<V, E> graph, V start, V end) {
        LinkedList<V> toCheck = new LinkedList<>();
        HashSet<V> visited = new HashSet<>();
        HashMap<V, E> h1 = new HashMap<>();
        visited.add(start);
        toCheck.add(start);
        while (!toCheck.isEmpty()) {
            V checkingVertex = toCheck.removeFirst();
            if (end.equals(checkingVertex)) {
                return this.backtrack(h1, end, start);
            }
            for (E transport : checkingVertex.getOutgoing()) {
                if (!visited.contains(transport.getTarget())) {
                    visited.add(transport.getTarget());
                    h1.put(transport.getTarget(), transport);
                    toCheck.addLast(transport.getTarget());
                }
            }
        }
        return new LinkedList<E>();
    }

    /**
     * Method that gets the reverse path from an end to start node
     * @param h1 hashmap
     * @param end the end vertex
     * @param start the start vertex
     * @return
     */
    public List<E> backtrack(HashMap<V, E> h1, V end, V start) {
        LinkedList<E> path = new LinkedList<E>();
        V currCity = end;
        while (currCity != start) {
            path.addFirst(h1.get(currCity));
            currCity = h1.get(currCity).getSource();
        }
        return path;
    }

    // TODO: feel free to add your own methods here!
    // hint: maybe you need to get a City by its name
}
