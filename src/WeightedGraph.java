import java.util.*;

public class WeightedGraph<V> {
    private final Map<V, Vertex<V>> vertices = new HashMap<>();
    private final boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V from, V to, double weight) {
        Vertex<V> v1 = getOrCreateVertex(from);
        Vertex<V> v2 = getOrCreateVertex(to);
        v1.addAdjacentVertex(v2, weight);
        if (!directed) {
            v2.addAdjacentVertex(v1, weight);
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
