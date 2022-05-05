package graph;

import java.util.*;

public class Graph<T> {
    public Graph(int numberOfVertex) {
        this.adjVertices = new HashMap<>(numberOfVertex);
    }

    static class Vertex<T> {
        T value;

        Vertex(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex<?> vertex = (Vertex<?>) o;

            return value.equals(vertex.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    '}';
        }

        public T getValue() {
            return value;
        }
    }

    Map<Vertex, List<Vertex>> adjVertices;

    /**
     * Add a new vertex in the graph without any edge connection
     * @param value
     */
    public void addVertex(T value) {
        adjVertices.putIfAbsent(new Vertex(value), new ArrayList<>());
    }

    /**
     * Remove vertex from the graph
     * Also remove the edge
     * @param value
     */
    public void removeVertex(T value) {
        adjVertices.values().stream().forEach(vertices -> vertices.remove(new Vertex(value)));
        adjVertices.remove(new Vertex(value));
    }

    /**
     * Add bi-directional edge between v1 and v2
     * Assume those 2 vertexes have been created in graph
     * @param value1
     * @param value2
     */
    public void addEdge(T value1, T value2) {
        Vertex vertex1 = adjVertices.keySet().stream().filter(vertex -> vertex.getValue().equals(value1))
                        .findAny().orElse(null);
        Vertex vertex2 = adjVertices.keySet().stream().filter(vertex -> vertex.getValue().equals(value2))
                .findAny().orElse(null);

        if (vertex1 != null && vertex2 != null) {
            adjVertices.get(vertex1).add(vertex2);
            adjVertices.get(vertex2).add(vertex1);
        }
    }

    /**
     * Remove bi-directional edge between v1 and v2
     * Assume those 2 vertexes have been created in graph
     * @param value1
     * @param value2
     */
    public void removeEdge(T value1, T value2) {
        Vertex vertex1 = adjVertices.keySet().stream().filter(vertex -> vertex.getValue().equals(value1))
                .findAny().orElse(null);
        Vertex vertex2 = adjVertices.keySet().stream().filter(vertex -> vertex.getValue().equals(value2))
                .findAny().orElse(null);

        if (vertex1 != null && vertex2 != null) {
            adjVertices.get(vertex1).remove(vertex2);
            adjVertices.get(vertex2).remove(vertex1);
        }
    }
}
