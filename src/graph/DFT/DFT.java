package graph.DFT;

import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;


/**
 * Deep First Traverse
 */
public class DFT {
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

        List<Integer> results = getDFT(graph, 2);
    }

    public static <T> List<T> getDFT(Graph<T> graph, T init) {
        Stack<T> buffer = new Stack<>();
        List<T> results = new ArrayList<>(graph.getTotalVertex());

        buffer.push(init);

        while (!buffer.isEmpty()) {
            T value = buffer.peek();

            // Added into result list if it's first time visit
            if (!results.contains(value)) {
                results.add(value);
            } else {
                // 2nd visit the same vertex, we know its connected vertexes are processed already
                buffer.pop();
                continue;
            }

            List<Graph.Vertex> allEdges = graph.getEdge(value);

            // this vertex has no edge
            if (allEdges.isEmpty()) {
                buffer.pop();
                continue;
            }

            for (Graph.Vertex vertex: allEdges) {
                T vertaxValue = (T) vertex.getValue();

                // Only add the connected vertexes when they are not in the results and stack already
                if (!buffer.contains(vertaxValue) && !results.contains(vertaxValue)) {
                    buffer.push(vertaxValue);
                }
            }
        }

        return results;
    }
}
