import java.util.*;
public class Vertex<T> {
    private final T data;
    private boolean visited;
    private List<Vertex<T>> neighbors = new ArrayList<>();
    public Vertex(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }
    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public List<Vertex<T>> getNeighbors() {
        return neighbors;
    }
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}