import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private final Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private final UnweightedGraph<V> graph;

    public DepthFirstSearch(UnweightedGraph<V> graph, V startValue) {
        this.graph = graph;
        Vertex<V> start = graph.getVertexByValue(startValue);
        if (start != null) {
            dfs(start, new HashSet<>());
        }
    }

    private void dfs(Vertex<V> current, Set<Vertex<V>> visited) {
        visited.add(current);
        for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
            if (!visited.contains(neighbor)) {
                edgeTo.put(neighbor, current);
                dfs(neighbor, visited);
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
