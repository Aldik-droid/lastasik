import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private final UnweightedGraph<V> graph;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V startValue) {
        this.graph = graph;
        Vertex<V> start = graph.getVertexByValue(startValue);
        if (start == null) return;

        Set<Vertex<V>> visited = new HashSet<>();
        Queue<Vertex<V>> queue = new LinkedList<>();

        visited.add(start);
        edgeTo.put(start, null);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V key) {
        Vertex<V> dest = graph.getVertexByValue(key);
        if (dest == null || !edgeTo.containsKey(dest)) return Collections.emptyList();

        LinkedList<V> path = new LinkedList<>();
        for (Vertex<V> v = dest; v != null; v = edgeTo.get(v)) {
            path.addFirst(v.getData());
        }
        return path;
    }
}
