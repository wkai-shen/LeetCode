package graph.BFT;

import graph.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Breadth First Traverse
 */
public class BFT {
    public static void main(String[] args) {
        final int totalVertexes = 10;
        Graph<Integer> graph = new Graph<>(totalVertexes);

        IntStream.range(1, totalVertexes + 1).forEach(i -> {
            graph.addVertex(i);
        });

        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(4, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 10);
        graph.addEdge(3, 9);

        graph.addEdge(2, 5);
        graph.addEdge(2, 7);
        graph.addEdge(2, 8);
        graph.addEdge(5, 8);
        graph.addEdge(5, 7);
        graph.addEdge(5, 6);
        graph.addEdge(8, 7);

        List<Integer> results = getBFT(graph, 1);
    }

    public static <T> List<T> getBFT(Graph<T> graph, T init) {
        int totalVertexes = graph.getTotalVertex();
        List<T> results = new ArrayList<>(totalVertexes);
        Queue<T> buffer = new ArrayDeque<>(totalVertexes);

        buffer.add(init);

        while (!buffer.isEmpty()) {
            T value = buffer.remove();

            results.add(value);

            List<Graph.Vertex> allEdges = graph.getEdge(value);

            // this vertex has no edge
            if (allEdges.isEmpty()) {
                buffer.remove();
                continue;
            }

            for (Graph.Vertex vertex: allEdges) {
                T vertaxValue = (T) vertex.getValue();

                if (!results.contains(vertaxValue) && !buffer.contains(vertaxValue)) {
                    buffer.add(vertaxValue);
                }
            }
        }

        return results;
    }
}
