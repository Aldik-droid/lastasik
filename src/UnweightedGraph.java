import java.util.*;

public class UnweightedGraph<V> {
    private final Map<V, Vertex<V>> vertices = new HashMap<>();
    private final boolean directed;

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V from, V to) {
        Vertex<V> v1 = getOrCreateVertex(from);
        Vertex<V> v2 = getOrCreateVertex(to);
        v1.addAdjacentVertex(v2, 1.0);
        if (!directed) {
            v2.addAdjacentVertex(v1, 1.0);
        }
    }

    private Vertex<V> getOrCreateVertex(V value) {
        return vertices.computeIfAbsent(value, Vertex::new);
    }

    public Vertex<V> getVertexByValue(V value) {
        return vertices.get(value);
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices.values();
    }
}
