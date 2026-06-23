import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstSearch {

    public <T> boolean CheckPathDFS(Vertex <T> s,  Vertex<T> f){
        if (s==f){
            return true;
        }
        s.setVisited(true);
        for (Vertex<T> neighbor : s.getNeighbors()){
            if (!neighbor.isVisited()){
                if (CheckPathDFS(neighbor,f)){
                    return true;
                }
            }    
        }
        return false;
    }
}
