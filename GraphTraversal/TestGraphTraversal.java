public class TestGraphTraversal {

    public static void main(String[] args) {
        Vertex<String> A = new Vertex<>("A");
        Vertex<String> B = new Vertex<>("B");
        Vertex<String> C = new Vertex<>("C");
        Vertex<String> D = new Vertex<>("D");
        Vertex<String> E = new Vertex<>("E");

        A.getNeighbors().add(B);
        A.getNeighbors().add(C);

        B.getNeighbors().add(A);
        B.getNeighbors().add(D);

        C.getNeighbors().add(A);
        C.getNeighbors().add(D);

        D.getNeighbors().add(B);
        D.getNeighbors().add(C);

        DepthFirstSearch dfs = new DepthFirstSearch();
        System.out.println("DFS: " + dfs. CheckPathDFS(A, D));

        A.setVisited(false);
        B.setVisited(false);
        C.setVisited(false);
        D.setVisited(false);

        BreadthFirstSearch bfs = new BreadthFirstSearch();
        System.out.println("BFS: " + bfs.CheckPathBFS(A, D));
        System.out.println("From A to E : ");
        System.out.println("DFS: " + dfs.CheckPathDFS(A, E));
        System.out.println("BFS: " + bfs.CheckPathBFS(A, E));
    }
}