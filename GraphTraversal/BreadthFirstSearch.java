import java.util.*;
public class BreadthFirstSearch {
    public <T> boolean CheckPathBFS(Vertex<T> s, Vertex<T> f){
        Queue<Vertex<T>> queue = new LinkedList<>();
        s.setVisited(true);
        queue.offer(s);
        while(!queue.isEmpty()){
            Vertex<T> current = queue.poll();
            if (current == f){
                return true;
            }
            for (Vertex<T> neighbor : current.getNeighbors()){
                if (! neighbor.isVisited()){
                    neighbor.setVisited(true);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }
}