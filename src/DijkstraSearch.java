import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private final Map<Vertex<V>, Double> distTo = new HashMap<>();
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V startValue) {
        this.graph = graph;
        Vertex<V> start = graph.getVertexByValue(startValue);
        if (start == null) return;

        distTo.put(start, 0.0);
        edgeTo.put(start, null);

        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distTo.get(current) + weight;

                if (newDist < distTo.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distTo.put(neighbor, newDist);
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
